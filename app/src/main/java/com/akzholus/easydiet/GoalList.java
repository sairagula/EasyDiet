package com.akzholus.easydiet;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class GoalList  extends ArrayAdapter <GoalVT>{

    private Activity context;
    private List<GoalVT> goalList;

    public GoalList (Activity context,List<GoalVT> goalList ){
        super(context, R.layout.goals,goalList);
        this.context = context;
        this.goalList = goalList;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        GoalVT goal = goalList.get(position);

        LayoutInflater inflater = context.getLayoutInflater();

        View listViewItem = inflater.inflate(R.layout.goals,null,true);

        TextView textView = (TextView) listViewItem.findViewById(R.id.textView);

        return listViewItem;
    }
}

//    import android.support.v7.widget.RecyclerView;
//    import android.view.LayoutInflater;
//    import android.view.View;
//    import android.view.ViewGroup;
//    import android.widget.TextView;
//
//    import com.akzholus.easydiet.R;
//    import com.akzholus.easydiet.charts.ArcChart;
//    import com.akzholus.easydiet.charts.GoalLineChart;
//    import com.akzholus.easydiet.valuetypes.GoalChartDataSetVT;
//    import com.akzholus.easydiet.valuetypes.GoalTextCardDataVT;
//
//
//public class MyGoalRecycleViewAdapter
//        extends RecyclerView.Adapter<MyGoalRecycleViewAdapter.MainViewHolder> {
//    private static final int TYPE_TEXT = 0;
//    private static final int TYPE_GRAPH = 1;
//
//    private final GoalTextCardDataVT textData;
//    private final GoalChartDataSetVT chartData;
//
//    public MyGoalRecycleViewAdapter(ParseGoal activeGoal) {
//        textData = GoalTextCardDataVT.fromParseGoal(activeGoal, WeightsDao.getInstance());
//        chartData = GoalChartDataSetVT.fromParseGoal(activeGoal);
//    }
//
//    @Override
//    public MainViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
//        switch (viewType) {
//            case TYPE_TEXT:
//                View textCard = inflater.inflate(R.layout.card_item_my_goal_text, viewGroup, false);
//                return new TextViewHolder(textCard);
//            default:
//                View graphCard = inflater.inflate(R.layout.card_item_my_goal_graph, viewGroup,
//                        false);
//                return new GraphViewHolder(graphCard);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(MainViewHolder viewHolder, int i) {
//        switch (viewHolder.getItemViewType()) {
//            case TYPE_TEXT:
//                TextViewHolder textCard = (TextViewHolder) viewHolder;
//                textCard.setData(textData);
//                break;
//            default:
//                GraphViewHolder confirmCard = (GraphViewHolder) viewHolder;
//                confirmCard.setData(chartData);
//        }
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return position == 0 ? TYPE_TEXT : TYPE_GRAPH;
//    }
//
//    @Override
//    public int getItemCount() {
//        return 2;
//    }
//
//    public class MainViewHolder extends RecyclerView.ViewHolder {
//        public MainViewHolder(View itemView) {
//            super(itemView);
//        }
//    }
//
//    class TextViewHolder extends MainViewHolder {
//        private ArcChart weightLostArcGraph;
//        private ArcChart remainingDaysArcGraph;
//        private TextView weightLostText;
//        private TextView remainingDaysText;
//        private TextView iCommitedMoneyText;
//
//        public TextViewHolder(View itemView) {
//            super(itemView);
//            View parentView = itemView.findViewById(R.id.activeGoalLinearLayoutId);
//
//            weightLostArcGraph = new ArcChart(
//                    (DecoView) parentView.findViewById(R.id.weight_lost_arc_graph));
//            weightLostText = (TextView) parentView.findViewById(R.id.weight_lost_text);
//            TextView weightLostTextSecondLine = (TextView) parentView.findViewById(R.id.weight_lost_text_second_line);
//            weightLostTextSecondLine.setText(String.format("%s lost", Settings.getInstance().getWeightUnit().toShortString()));
//
//            remainingDaysArcGraph = new ArcChart(
//                    (DecoView) parentView.findViewById(R.id.remaining_days_arc_graph));
//            remainingDaysText = (TextView) parentView.findViewById(R.id.remaining_days_text);
//
//            iCommitedMoneyText = (TextView) parentView.findViewById(R.id.my_goal_i_commited_money_text);
//        }
//
//        public void setData(GoalTextCardDataVT data) {
//            weightLostArcGraph.updateData(
//                    data.getTotalTargetInPounds(), // max
//                    data.getLostWeightSinceStartInPounds()); // current
//            weightLostText.setText(String.format("%.1f", data.getLostWeightSinceStartInPounds()));
//
//            remainingDaysArcGraph.updateData(
//                    data.getGoalDurationInDays(), // max
//                    data.getGoalDurationInDays() - data.getRemainingDurationInDays()); // current
//            remainingDaysText.setText(String.valueOf(data.getRemainingDurationInDays()));
//
//            iCommitedMoneyText.setText(String.format(
//                    iCommitedMoneyText.getContext().getString(R.string.my_goal_i_commited_string),
//                    data.getPledgeAmount(),
//                    data.getRefereeEmailAddress()));
//        }
//    }
//
//    class GraphViewHolder extends MainViewHolder {
//        private GoalLineChart goalChart;
//
//        public GraphViewHolder(View itemView) {
//            super(itemView);
//            LineChart chart = (LineChart) itemView.findViewById(R.id.goalChartId);
//            goalChart = new GoalLineChart(chart, itemView);
//            goalChart.setVisualStyle();
//        }
//
//        public void setData(GoalChartDataSetVT data) {
//            goalChart.setData(data);
//        }
//    }
//
//}
