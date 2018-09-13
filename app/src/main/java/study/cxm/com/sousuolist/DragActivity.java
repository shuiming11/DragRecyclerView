package study.cxm.com.sousuolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;

import java.util.ArrayList;
import java.util.List;

public class DragActivity extends AppCompatActivity {
    private RecyclerView rv;
    List<ImageModel> toolModels;
    ImageAdapter toolsAdapter;
    private ImageView imageView;
    private List<Integer> lastPos;
    private int[] resIds = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
    private List<View> lastView;
    private int tempId;
    private int lastPosition;

    /**
     * 滑动监听
     */
    OnItemDragListener onItemDragListener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos){

            //拿到当前准备滑动的View，一点击就选中
            View iv = (ImageView)toolsAdapter.getViewByPosition(rv,pos,R.id.iv_tool);
            iv.setSelected(true);

            //上来默认上一个被点击的是第一个imageView
            if(lastPosition == 0){
                lastPos.add(0);
                lastView.add(toolsAdapter.getViewByPosition(rv,0,R.id.iv_tool));
            }
            View last = lastView.get(0);

            //点击了另一个imageView，改变上一个imageView的状态
            if(lastPos.get(0) != pos){
                toolsAdapter.getViewByPosition(rv,lastPos.get(0),R.id.iv_tool).setSelected(false);
            }
            lastPos.clear();
            lastPos.add(pos);

            lastPosition = pos;


        }
        @Override
        public void onItemDragMoving(RecyclerView.ViewHolder source, int from, RecyclerView.ViewHolder target, int to) {
            Glide.with(DragActivity.this).load(resIds[from]).asBitmap().into(imageView) ;

            //拖动过程交换两个imageview的id
            tempId = resIds[to];
            resIds[to] = resIds[from];
            resIds[from] = tempId;
            source.itemView.setSelected(true);
            target.itemView.setSelected(false);

            //拖动结束后维护上一个滑动的位置值
            lastPos.clear();
            lastPos.add(to);


        }
        @Override
        public void onItemDragEnd(RecyclerView.ViewHolder viewHolder, int pos) {
            View iv = (ImageView)toolsAdapter.getViewByPosition(rv,pos,R.id.iv_tool);
            //滑动结束后得到上一次的imageView
            lastView.clear();
            lastView.add(iv);
            Glide.with(DragActivity.this).load(resIds[pos]).asBitmap().into(imageView) ;







        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        lastView = new ArrayList<>();
        lastPos = new ArrayList<>();

        //默认加载第一张图片
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        Glide.with(DragActivity.this).load(resIds[0]).asBitmap().into(imageView) ;
        rv = findViewById(R.id.rv);
        int resIDd = R.drawable.report;

        toolModels = new ArrayList<>();
        ImageModel toolModel = new ImageModel(resIDd);
        ImageModel toolModel2 = new ImageModel(resIDd);
        ImageModel toolModel3 = new ImageModel(resIDd);
        ImageModel toolModel4 = new ImageModel(resIDd);
        ImageModel toolModel5 = new ImageModel(resIDd);
        toolModels.add(toolModel);
        toolModels.add(toolModel2);
        toolModels.add(toolModel3);
        toolModels.add(toolModel4);
        toolModels.add(toolModel5);
        toolsAdapter = new ImageAdapter(toolModels,resIds);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(toolsAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setLayoutManager(new GridLayoutManager(this,4));
        rv.setAdapter(toolsAdapter);

         //开启拖拽
        toolsAdapter.enableDragItem(itemTouchHelper, R.id.iv_tool, false);
        toolsAdapter.setOnItemDragListener(onItemDragListener);


    }
}
