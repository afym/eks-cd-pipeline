package logger;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrackerEntity {
    private static final String DATE_FORMAT = "yyyy/MM/dd";
    private static final String HOUR_FORMAT = "hhmmss";
    HttpServletRequest request;
    private String ipAddress = "0.0.0.0";
    private String date;
    private String hour;

    public TrackerEntity(HttpServletRequest request) {
        this.setDateValues();
        this.request = request;
        this.setIpAddress();
    }

    public TrackerEntity() {
        this.setDateValues();
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public String getDate() {
        return date;
    }

    public String getHour() {
        return hour;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.date).append("::")
                .append(this.hour).append("::").append(this.ipAddress);

        return builder.toString();
    }

    private void setDateValues() {
        this.date = this.getDateResultFromFormat(TrackerEntity.DATE_FORMAT);
        this.hour = this.getDateResultFromFormat(TrackerEntity.HOUR_FORMAT);
    }

    private String getDateResultFromFormat(String format) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    private void setIpAddress() {
        this.ipAddress = request.getHeader("x-forwarded-for");

        if (this.ipAddress == null || this.ipAddress.length() == 0 || "unknown".equalsIgnoreCase(this.ipAddress)) {
            this.ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (this.ipAddress == null || this.ipAddress.length() == 0 || "unknown".equalsIgnoreCase(this.ipAddress)) {
            this.ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }

        if (this.ipAddress == null || this.ipAddress.length() == 0 || "unknown".equalsIgnoreCase(this.ipAddress)) {
            this.ipAddress = request.getRemoteAddr();
            if (this.ipAddress.equals("127.0.0.1") || this.ipAddress.equals("0:0:0:0:0:0:0:1")) {
                InetAddress inetAddress = null;
                try {
                    inetAddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                this.ipAddress = inetAddress.getHostAddress();
            }
        }

        if (this.ipAddress != null && this.ipAddress.length() > 15) {
            if (this.ipAddress.indexOf(",") > 0) {
                this.ipAddress = this.ipAddress.substring(0, this.ipAddress.indexOf(","));
            }
        }
    }
}