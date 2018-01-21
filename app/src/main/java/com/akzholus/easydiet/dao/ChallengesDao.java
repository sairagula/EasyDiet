package com.akzholus.easydiet.dao;

import java.util.ArrayList;
import java.util.List;

import com.akzholus.easydiet.R;
import com.akzholus.easydiet.valuetypes.ChallengeContentSectionVT;
import com.akzholus.easydiet.valuetypes.ChallengeVT;

public class ChallengesDao {
    public static ChallengeVT getCurrentChallenge() {
        List<ChallengeContentSectionVT> sections = new ArrayList<>();
        sections.add(new ChallengeContentSectionVT("Why?",
                "Because if you want to eat later in the night, "
                        + "chances are your laziness will win over your desire to eat. "
                        + "Try it for 7 days and keep note of its effects."));
        sections.add(new ChallengeContentSectionVT("How?",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse tempus neque quis lectus consequat, quis elementum neque viverra. Curabitur aliquet fermentum augue, a auctor turpis placerat eu. Phasellus eget euismod odio. Pellentesque eu est vel augue rutrum ullamcorper id at lectus. Phasellus pharetra fringilla tellus a maximus. Phasellus egestas velit non orci interdum aliquet. Sed nec rhoncus odio. Vivamus consectetur mauris mauris, sit amet aliquet nisl mattis et. Curabitur venenatis egestas vulputate. Integer ac rutrum augue. "
                        + "\n\nNulla molestie gravida quam ac rhoncus. Vivamus nibh mi, rhoncus at sapien ut, vestibulum pretium leo. Donec non varius nibh. Phasellus ultrices viverra risus vel gravida. Curabitur et urna ac nulla auctor bibendum id pharetra mauris. Sed sed rutrum sapien, sed consectetur augue. In hac habitasse platea dictumst. Pellentesque a auctor nulla. Mauris in eros elementum libero varius dictum. Vivamus sagittis fringilla purus. Sed nunc augue, imperdiet nec laoreet sit amet, pulvinar eget velit. Curabitur turpis turpis, pharetra et nisl eget, condimentum tincidunt enim."));
        return new ChallengeVT(R.drawable.brush_your_teeth,
                "Week 1: Brush you teeth RIGHT after dinner for 7 days", sections);
    }

}