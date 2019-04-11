package com.hengchang.ui_component.adapter.recycleradapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.hengchang.ui_component.adapter.OnItemClickListener;
import com.hengchang.ui_component.adapter.OnLongClickListener;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author: zhangzhilong
 * @date: 2018/5/17
 * @des:   RecyclerView公共Adapter
 */
public abstract class UIRecyclerAdapter<T> extends RecyclerView.Adapter<UIRecyclerViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected List<T> mData;

    private int mLayoutId;

    // 多布局支持
    private UIMultiTypeSupport mMultiTypeSupport;

    public UIRecyclerAdapter(Context context, List<T> data, int layoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mData = data;
        this.mLayoutId = layoutId;
    }

    /**
     * 多布局支持
     */
    public UIRecyclerAdapter(Context context, List<T> data, UIMultiTypeSupport<T> multiTypeSupport) {
        this(context, data, -1);
        this.mMultiTypeSupport = multiTypeSupport;
    }

    /**
     * 根据当前位置获取不同的viewType
     */
    @Override
    public int getItemViewType(int position) {
        if (mMultiTypeSupport != null) {
            return mMultiTypeSupport.getLayoutId(mData.get(position), position);
        }
        return super.getItemViewType(position);
    }

    @Override
    public UIRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 多布局支持
        if (mMultiTypeSupport != null) {
            mLayoutId = viewType;
        }

        View itemView = mInflater.inflate(mLayoutId, parent, false);

        UIRecyclerViewHolder holder = new UIRecyclerViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final UIRecyclerViewHolder holder, final int position) {
        // 设置点击和长按事件
        if (mItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mItemClickListener.onItemClick(holder.getAdapterPosition());
                }
            });
        }
        if (mLongClickListener != null) {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return mLongClickListener.onLongClick(holder.getAdapterPosition());
                }
            });
        }

        convert(holder, mData.get(position));
    }


    public abstract void convert(UIRecyclerViewHolder holder, T item);


    @Override
    public int getItemCount() {
        return mData.size();
    }


    public void setData(List<T> list) {
        mData = (list != null) ? list : new ArrayList<T>();
        notifyDataSetChanged();
    }


    public void delete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }


    public void add(T data) {
        mData.add(data);
        notifyDataSetChanged();
    }


    public void add(int position, T data) {
        mData.add(position, data);
        notifyItemInserted(position);
    }


    public void addAll(List<T> list) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.addAll(list);
        notifyDataSetChanged();
    }


    /***************
     * 给条目设置点击和长按事件
     *********************/
    public OnItemClickListener mItemClickListener;
    public OnLongClickListener mLongClickListener;

    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mItemClickListener = itemClickListener;
    }

    public void setOnLongClickListener(OnLongClickListener longClickListener) {
        this.mLongClickListener = longClickListener;
    }
}
