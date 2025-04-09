package com.moamenproject.accounts.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware{

    @Override
    public Optional getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }

}
