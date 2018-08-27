package devops;

import infrastructure.TrackerRepository;
import logger.TrackerEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TrackerController {
    private static final String TRACKER = "Your activity was tracked : ";
    private TrackerEntity trackerEntity = new TrackerEntity();
    private TrackerRepository trackerRepository;

    @RequestMapping("/tracker")
    public String index(){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            this.trackerEntity = new TrackerEntity(request);
            // trackerRepository = new TrackerRepository();
            return TRACKER + this.trackerEntity.toString();
        }

        return TRACKER + this.trackerEntity.toString();
    }
}
