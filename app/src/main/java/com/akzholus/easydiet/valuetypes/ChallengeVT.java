package com.akzholus.easydiet.valuetypes;

import java.util.List;


public class ChallengeVT {
    private final int drawableId;
    private final String subject;
    private final List<ChallengeContentSectionVT> sections;

    public ChallengeVT(int drawableId, String subject,
                       List<ChallengeContentSectionVT> sections) {
        assert (subject != null);
        assert (sections != null);
        this.drawableId = drawableId;
        this.subject = subject;
        this.sections = sections;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public String getSubject() {
        return subject;
    }

    public List<ChallengeContentSectionVT> getSections() {
        return sections;
    }
}