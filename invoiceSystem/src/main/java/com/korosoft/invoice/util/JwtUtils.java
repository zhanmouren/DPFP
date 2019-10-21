package com.korosoft.invoice.util;

import java.util.Calendar;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.korosoft.invoice.bean.LoginBean;

//登陆生成token比对
public class JwtUtils {
	
	private static final String KEY = "admin";
	//生成token
	public static String createJWT(int minute, LoginBean user) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Calendar calendar = Calendar.getInstance();
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put("id", user.getU_id());
		claims.put("loginName", user.getLoginName());
		String subject = user.getLoginName();
		JwtBuilder builder = Jwts.builder()
				.setClaims(claims)
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(calendar.getTime())
				.setSubject(subject)
				.signWith(signatureAlgorithm, KEY);
		if (minute >= 0) {
			calendar.add(Calendar.MINUTE, minute);
			builder.setExpiration(calendar.getTime());
		}
		return builder.compact();
	}

	/**
	 * Token的解密
	 * 
	 * @param token
	 *            加密后的token
	 * @param user
	 *            用户的对象
	 * @return
	 */
	public static Claims parseJWT(String token) {
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
	}
	
	public static boolean isExpired(String token) {
		try {
			Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
		} catch (io.jsonwebtoken.ExpiredJwtException e) {
			return true;
		}
		return false;
	}
}
