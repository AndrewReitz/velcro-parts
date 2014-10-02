package com.andrewreitz.velcro.infinitescroll;

import android.widget.AbsListView;

/**
 * Scroll listener to be extended for infinite scrolling.
 * See http://www.avocarrot.com/blog/implement-infinitely-scrolling-list-android
 *
 * @author Panayiotis Papageorgiou
 * @author Andrew Reitz
 */
public abstract class InfiniteScrollListener implements AbsListView.OnScrollListener {
  private final int bufferItemCount;

  private int currentPage = 0;
  private int itemCount = 0;
  private boolean isLoading = true;

  /**
   * @param bufferItemCount The number of items to keep pre-fetched. For example if this is number
   * was set to 5, the user would be able to see the items on screen and there would be 5 views
   * below for them to scroll too.
   */
  public InfiniteScrollListener(int bufferItemCount) {
    this.bufferItemCount = bufferItemCount;
  }

  /**
   * Method that is called when more items should be loaded.
   *
   * @param page The current page the user is on. This is based off of the number of items given to
   * adapter, pre load request. The initial load would be zero and each time loadMore is called the
   * page is increased by one.
   * @param totalItemsCount The total number of items that have been loaded into the adapter this
   * scroll listener is atached to.
   */
  protected abstract void loadMore(int page, int totalItemsCount);

  /** {@inheritDoc} */
  @Override public void onScrollStateChanged(AbsListView view, int scrollState) {
    // Do Nothing
  }

  /** {@inheritDoc} */
  @Override public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
      int totalItemCount) {
    if (totalItemCount < itemCount) {
      this.itemCount = totalItemCount;
      if (totalItemCount == 0) {
        this.isLoading = true;
      }
    }

    if (isLoading && (totalItemCount > itemCount)) {
      isLoading = false;
      itemCount = totalItemCount;
      currentPage++;
    }

    if (!isLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + bufferItemCount)) {
      loadMore(currentPage + 1, totalItemCount);
      isLoading = true;
    }
  }
}

