package liuliangqi.fragmentpractice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by liuliangqi on 2017/5/1.
 */

public class ListFragment extends android.support.v4.app.Fragment {

    public interface OnItemClickedListener{
        void onItemCliked(int position);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        OnItemClickedListener onItemClickedListener = (OnItemClickedListener) getActivity();
        // 第三个参数设置为false, onCreateView返回的时候会主动绑定到 container 上， 所以如果不指定的话，会绑定两次
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.listRecyclerView);
        ListAdapter mListAdapter = new ListAdapter(onItemClickedListener);

        mRecyclerView.setAdapter(mListAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        return view;
    }
}
