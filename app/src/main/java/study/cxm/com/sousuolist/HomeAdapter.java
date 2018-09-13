package study.cxm.com.sousuolist;

import android.support.annotation.Nullable;
import android.util.Log;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class HomeAdapter extends BaseQuickAdapter<ItemModel, BaseViewHolder> {
    private List<ItemModel> s;
    private Boolean isShow = false;
    public HomeAdapter(@Nullable List<ItemModel> s) {
        super(R.layout.item,s);
        this.s = s;
    }

    public Boolean getShow() {
        return isShow;
    }

    public void setShow(Boolean show) {
        isShow = show;
    }

    @Override
    protected void convert(BaseViewHolder helper, ItemModel s) {
        helper.setText(R.id.tv_item, s.getTaskName());
        Log.i("hahah",s.getTaskName());
    }

    @Override
    public int getItemCount() {
        if(isShow){
            return s.size();
        }
        return 3;

    }


}

