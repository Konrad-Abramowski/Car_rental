package org.company.model.enums;

public enum Job {
    ACCOUNTANT("accountant"),
    SERVICE_TECHNICAN("service technican");

    private String jobType;

    Job(final String jobType) {
        this.jobType = jobType;
    }
}
