package cn.tedu.mall.ams.security.filter;

import cn.tedu.mall.common.pojo.domain.CsmallAuthenticationInfo;
import cn.tedu.mall.common.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>单点登录过滤器</p>
 */
@Component
@Slf4j
public class SSOFilter extends OncePerRequestFilter {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private JwtTokenUtils jwtTokenUtils;
    @Value("${jwt.tokenHead}")
    private String jwtTokenHead;

    private static final String REQUEST_HEADER_AUTHORIZATION = "Authorization";

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws ServletException, IOException {


        // 查看所有到达当前系统请求是否有认证信息，有则存放context的数据
        // 获取AUTHORIZATION头
        String authHeader = httpServletRequest.getHeader(REQUEST_HEADER_AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(jwtTokenHead)) {
            String authToken = authHeader.substring(jwtTokenHead.length());
            //从redis验证是否已经退出登录
            //写入redis 锁住 这里采用list分日期存储,方便后续定时清理
            String lockedTokenList="token_list_.lock";
            Boolean member = stringRedisTemplate.boundSetOps(lockedTokenList).isMember(authToken);
            if(member){
                log.info("从redis拿到登录的token:"+authToken);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
                return;
            }
            CsmallAuthenticationInfo userInfo = jwtTokenUtils.getUserInfo(authToken);
            UsernamePasswordAuthenticationToken authentication=null;
            if (userInfo != null) {
                List<String> authoritiesString=userInfo.getAuthorities();
                List<GrantedAuthority> authorities=new ArrayList<>();
                for (String authorityValue : authoritiesString) {
                    authorities.add(new SimpleGrantedAuthority(authorityValue));
                }
                authentication=
                        new UsernamePasswordAuthenticationToken(userInfo.getUsername(),userInfo,authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                /*throw new CoolSharkServiceException(ResponseCode.UNAUTHORIZED,"您的token不正确,请重新登录");*/
                SecurityContextHolder.setContext(SecurityContextHolder.createEmptyContext());
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
