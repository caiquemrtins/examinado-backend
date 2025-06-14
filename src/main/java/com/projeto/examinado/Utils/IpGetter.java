package com.projeto.examinado.Utils;

import jakarta.servlet.http.HttpServletRequest;

public class IpGetter {


    public static String getUserIp (HttpServletRequest request){
        String ip = request.getHeader("X-Forwarded-For");
        if (ip==null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip==null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        // Pega o primeiro IP se houver múltiplos (ex: "IP1, IP2")
        if (ip != null && ip.contains(",")) {
            ip = ip.split(",")[0].trim();
        }

        return ip;

    }
}
