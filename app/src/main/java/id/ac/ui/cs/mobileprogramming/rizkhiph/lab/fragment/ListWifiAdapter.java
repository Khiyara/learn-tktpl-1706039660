package id.ac.ui.cs.mobileprogramming.rizkhiph.lab.fragment;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.R;
import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.databinding.WifiDetailBinding;
import id.ac.ui.cs.mobileprogramming.rizkhiph.lab.model.AccessPoint;

public class ListWifiAdapter extends RecyclerView.Adapter<ListWifiAdapter.ListViewHolder> {

    List<? extends AccessPoint> mAccessPointList;

    public void setList(final List<? extends AccessPoint> accessPoints) {
        if (mAccessPointList == null) {
            mAccessPointList = accessPoints;
            notifyItemRangeChanged(0, accessPoints.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mAccessPointList.size();
                }

                @Override
                public int getNewListSize() {
                    return accessPoints.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return accessPoints.get(newItemPosition).getName()
                            .equals(mAccessPointList.get(oldItemPosition).getName());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    AccessPoint newAccessPoint = accessPoints.get(newItemPosition);
                    AccessPoint oldAccessPoint = mAccessPointList.get(oldItemPosition);
                    return newAccessPoint.getName().equals(oldAccessPoint.getName()) &&
                            newAccessPoint.getMac().equals(oldAccessPoint.getMac()) &&
                            newAccessPoint.getStrength() == (oldAccessPoint.getStrength());
                }
            });
            mAccessPointList = accessPoints;
            result.dispatchUpdatesTo(this);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WifiDetailBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.wifi_detail, parent, false);

        return new ListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.binding.setAccessPoint(mAccessPointList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mAccessPointList == null ? 0 : mAccessPointList.size();
    }

    static class ListViewHolder extends RecyclerView.ViewHolder {

        final WifiDetailBinding binding;

        ListViewHolder(WifiDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
