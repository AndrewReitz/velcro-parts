package com.andrewreitz.velcro;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A recycler view adapter that makes life better.
 *
 * @param <I> The backing data model for the views.
 */
public abstract class BindableRecyclerAdapter<I>
    extends RecyclerView.Adapter<BindableRecyclerAdapter.$GarbageViewHolder> {

  /**
   * Create a new view for the view type.
   *
   * @param inflater inflater to use for inflating new views.
   * @param viewType user defined groupId to specify what view to return. See
   *                 {@link android.support.v7.widget.RecyclerView.Adapter#getItemViewType(int)}
   * @param parent   The parent view of the view being created.
   * @return a newly created view.
   */
  public abstract View newView(LayoutInflater inflater, int viewType, ViewGroup parent);

  /**
   * Retrieve an item from the data store backing this adapter.
   *
   * @param position the position in the list to get the data for.
   * @return the retrieve item for the specified position.
   */
  public abstract I getItem(int position);

  /**
   * Bind the data from the item into the view.
   *
   * @param item     the item whom's data will be bound to the view for displaying relevant content.
   * @param view     the view to display the data in.
   * @param position the position in the view list.
   */
  public abstract void bindView(I item, View view, int position);

  @Override
  public final $GarbageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new $GarbageViewHolder(newView(LayoutInflater.from(parent.getContext()),
        viewType, parent));
  }


  @Override
  public final void onBindViewHolder($GarbageViewHolder holder, int position) {
    bindView(getItem(position), holder.itemView, position);
  }

  /**
   * A garbage view holder that does nothing...
   */
  static final class $GarbageViewHolder extends RecyclerView.ViewHolder {
    public $GarbageViewHolder(View view) {
      super(view);
    }
  }
}
