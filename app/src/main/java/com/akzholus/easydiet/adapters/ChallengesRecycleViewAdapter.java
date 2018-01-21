package com.akzholus.easydiet.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.akzholus.easydiet.R;
import com.akzholus.easydiet.common.Constants;
import com.akzholus.easydiet.valuetypes.ChallengeContentSectionVT;
import com.akzholus.easydiet.valuetypes.ChallengeVT;

public class ChallengesRecycleViewAdapter extends
        RecyclerView.Adapter<ChallengesRecycleViewAdapter.ViewHolder> {

    private final ChallengeVT currentChallenge;

    public ChallengesRecycleViewAdapter(ChallengeVT currentChallenge) {
        this.currentChallenge = currentChallenge;
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int arg1) {
        viewHolder.setData(currentChallenge);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View challengesCard = inflater.inflate(R.layout.card_item_challenge,
                viewGroup, false);
        return new ViewHolder(challengesCard);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout parent;

        public ViewHolder(View itemView) {
            super(itemView);
            this.parent = (LinearLayout) itemView
                    .findViewById(R.id.challengeCardLinearLayoutId);
        }

        public void setData(ChallengeVT currentChallenge) {
            // Set image
            ImageView image = (ImageView) parent
                    .findViewById(R.id.challenge_image_id);
            image.setImageResource(currentChallenge.getDrawableId());

            // Set subject
            AppCompatTextView subject = (AppCompatTextView) parent
                    .findViewById(R.id.challenge_subject_id);
            subject.setText(currentChallenge.getSubject());

            // Set sections
            int marginPx = (int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, Constants.TEXT_MARGING, parent
                            .getContext().getResources().getDisplayMetrics());
            for (ChallengeContentSectionVT section : currentChallenge
                    .getSections()) {
                LayoutParams headerLp = new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                headerLp.setMargins(marginPx, marginPx, marginPx, 0);
                AppCompatTextView header = new AppCompatTextView(
                        parent.getContext());
                header.setText(section.getHeading());
                header.setTextAppearance(parent.getContext(),
                        R.style.MyTitleTextStyle);
                header.setLayoutParams(headerLp);
                parent.addView(header);

                LayoutParams contentLp = new LayoutParams(
                        LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                contentLp.setMargins(marginPx, 0, marginPx, marginPx);
                AppCompatTextView sectionContent = new AppCompatTextView(
                        parent.getContext());
                sectionContent.setText(section.getSectionContent());
                sectionContent.setTextAppearance(parent.getContext(),
                        R.style.MyTextStyle);
                sectionContent.setLayoutParams(contentLp);
                parent.addView(sectionContent);
            }

        }
    }

}