package purrfectmate.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    // private static final Logger logger = LoggerFactory.getLogger(SecurityService.class);

    public boolean hasUserId(Long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserPrincipal) {
            Long currentUserId = ((UserPrincipal) principal).getUserId();

            // logger.info("Checking access for user ID: {}", currentUserId);
            // logger.info("Requested user ID: {}", userId);

            boolean hasAccess = currentUserId.equals(userId);
            // logger.info("Access granted: {}", hasAccess);
            return hasAccess;
        }

        // logger.warn("Principal is not an instance of DriverPrincipal");
        return false;
    }
}
