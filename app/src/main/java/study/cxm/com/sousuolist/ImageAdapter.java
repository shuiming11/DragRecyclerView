package study.cxm.com.sousuolist;

import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


public class ImageAdapter extends BaseItemDraggableAdapter<ImageModel, BaseViewHolder> {
    private List<ImageModel> toolModels;
    private int[] resIds;
    public ImageAdapter(@Nullable List<ImageModel> toolModels, int[] resIds) {
        super(R.layout.item_image,toolModels);
        this.toolModels = toolModels;
        this.resIds = resIds;
    }



    @Nullable
    @Override
    public View getViewByPosition(int position, int viewId) {
        return super.getViewByPosition(position, viewId);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void convert(BaseViewHolder helper, ImageModel toolModel) {
//        helper.setImageResource(R.id.iv_tool, toolModel.getResid());
        ImageView iv = helper.getView(R.id.iv_tool);

        //默认第一个图片为选中状态
        if(helper.getPosition() == 0){
            iv.setSelected(true);
        }
        Glide.with(iv.getContext()).load(resIds[helper.getPosition()]).asBitmap().centerCrop().into(iv) ;

    }

    @Override
    public int getItemCount() {
        return 4;
    }


}

