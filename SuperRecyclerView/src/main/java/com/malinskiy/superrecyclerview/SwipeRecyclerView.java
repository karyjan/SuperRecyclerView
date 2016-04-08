package com.malinskiy.superrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.malinskiy.superrecyclerview.swipe.SwipeDismissRecyclerViewTouchListener;

public class SwipeRecyclerView extends SuperRecyclerView {
    private RecyclerView.OnScrollListener mSwipeDismissScrollListener;

    public SwipeRecyclerView(Context context) {
        super(context);
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SwipeRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * Implement this method to customize the AbsListView
     */
    protected void initRecyclerView(View view) {
        super.initRecyclerView(view);
        RecyclerView.OnScrollListener mSwipeOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (mSwipeDismissScrollListener != null)
                    mSwipeDismissScrollListener.onScrolled(recyclerView, dx, dy);
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (mSwipeDismissScrollListener != null)
                    mSwipeDismissScrollListener.onScrollStateChanged(recyclerView, newState);
            }
        };

        mRecycler.addOnScrollListener(mSwipeOnScrollListener);
    }

    public void setupSwipeToDismiss(final SwipeDismissRecyclerViewTouchListener.DismissCallbacks listener) {
        SwipeDismissRecyclerViewTouchListener touchListener =
                new SwipeDismissRecyclerViewTouchListener(mRecycler, new SwipeDismissRecyclerViewTouchListener.DismissCallbacks() {
                    @Override
                    public boolean canDismiss(int position) {
                        return listener.canDismiss(position);
                    }

                    @Override
                    public void onDismiss(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        listener.onDismiss(recyclerView, reverseSortedPositions);
                    }
                });
        mSwipeDismissScrollListener = touchListener.makeScrollListener();
        mRecycler.setOnTouchListener(touchListener);
    }
}
