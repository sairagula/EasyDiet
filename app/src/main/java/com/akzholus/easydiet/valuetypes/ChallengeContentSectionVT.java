package com.akzholus.easydiet.valuetypes;

public class ChallengeContentSectionVT {
    private final String sectionHeading;
    private final String sectionContent;

    public ChallengeContentSectionVT(String heading, String content) {
        assert (heading != null);
        assert (content != null);
        this.sectionHeading = heading;
        this.sectionContent = content;
    }

    public String getHeading() {
        return sectionHeading;
    }

    public String getSectionContent() {
        return sectionContent;
    }

}