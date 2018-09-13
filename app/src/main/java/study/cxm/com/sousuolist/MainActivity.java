package study.cxm.com.sousuolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.callback.ItemDragAndSwipeCallback;
import com.chad.library.adapter.base.listener.OnItemDragListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    MyRecyclerViewAdapter adapter;
    HomeAdapter adapter2;
    List<String> data;
    List<ItemModel> models;
    List<ToolModel> toolModels;
    ToolsAdapter toolsAdapter;
    private int currentPosition;
    private ImageView imageView;
    private List<Integer> lastPos;
    private int[] resIds = {R.drawable.a,R.drawable.b,R.drawable.c,R.drawable.d};
    private List<View> lastView;

    /**
     * 滑动监听
     */
    OnItemDragListener onItemDragListener = new OnItemDragListener() {
        @Override
        public void onItemDragStart(RecyclerView.ViewHolder viewHolder, int pos){

            //拿到当前准备滑动的View，一点击就选中
            View iv = (ImageView)toolsAdapter.getViewByPosition(rv,pos,R.id.iv_tool);
            iv.setSelected(true);

            //上来默认上一个被点击的为第一个imageView
            if(lastPosition == 0){
                lastPos.add(0);
                lastView.add(toolsAdapter.getViewByPosition(rv,0,R.id.iv_tool));
            }
            View last = lastView.get(0);

//            //点击了另一个imageView，改变上一个imageView的状态
//            if(lastView.size()>0 && last != toolsAdapter.getViewByPosition(rv,pos,R.id.iv_tool)){
//                lastView.get(0).setSelected(false);
//            }

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
            Glide.with(MainActivity.this).load(resIds[from]).asBitmap().into(imageView) ;
            //拖动过程交换两个imageview的id
            tempId = resIds[to];
            resIds[to] = resIds[from];
            resIds[from] = tempId;
            currentPosition = to;
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
            Glide.with(MainActivity.this).load(resIds[pos]).asBitmap().into(imageView) ;







        }
    };
    private int tempId;
    private int lastPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        lastView = new ArrayList<>();
        lastPos = new ArrayList<>();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
        Glide.with(MainActivity.this).load(resIds[0]).asBitmap().into(imageView) ;
        rv = findViewById(R.id.rv);
        int resIDd = R.drawable.report;
        TextView tvMore = findViewById(R.id.tv_more);
        TextView tvHead = findViewById(R.id.tv);
        data = new ArrayList<>();
        models = new ArrayList<>();
        toolModels = new ArrayList<>();
//        ItemModel model = new ItemModel("1","2","3");
//        ItemModel mode2 = new ItemModel("4","5","6");
//        ItemModel mode3 = new ItemModel("4","5","6");
//        ItemModel mode4 = new ItemModel("4","5","6");
//        ItemModel mode5 = new ItemModel("4","5","6");
//        ItemModel mode6 = new ItemModel("4","5","6");
//        ItemModel mode7 = new ItemModel("4","5","6");
//        ItemModel mode8 = new ItemModel("4","5","6");
//        ItemModel mode9 = new ItemModel("4","5","6");
//        ItemModel mode10 = new ItemModel("4","5","6");
//        ItemModel mode11 = new ItemModel("4","5","6");
//        ItemModel mode12 = new ItemModel("4","5","6");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        data.add("2");
//        models.add(model);
//        models.add(mode2);
//        models.add(mode3);
//        models.add(mode4);
//        models.add(mode5);
//        models.add(mode6);
//        models.add(mode7);
//        models.add(mode8);
//        models.add(mode9);
//        models.add(mode10);
//        models.add(mode11);
//        models.add(mode12);
        ToolModel toolModel = new ToolModel(resIDd);
        ToolModel toolModel2 = new ToolModel(resIDd);
        ToolModel toolModel3 = new ToolModel(resIDd);
        ToolModel toolModel4 = new ToolModel(resIDd);
        ToolModel toolModel5 = new ToolModel(resIDd);
        toolModels.add(toolModel);
        toolModels.add(toolModel2);
        toolModels.add(toolModel3);
        toolModels.add(toolModel4);
        toolModels.add(toolModel5);
        toolsAdapter = new ToolsAdapter(toolModels,resIds);
        ItemDragAndSwipeCallback itemDragAndSwipeCallback = new ItemDragAndSwipeCallback(toolsAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemDragAndSwipeCallback);
        itemTouchHelper.attachToRecyclerView(rv);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setLayoutManager(new GridLayoutManager(this,4));
        rv.setAdapter(toolsAdapter);
         //开启拖拽
        toolsAdapter.enableDragItem(itemTouchHelper, R.id.iv_tool, false);
        toolsAdapter.setOnItemDragListener(onItemDragListener);
        toolsAdapter.setOnItemClickListener(new BaseItemDraggableAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(MainActivity.this, "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }

        });



//        rv.setLayoutManager(new LinearLayoutManager(this));
////        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//        adapter = new MyRecyclerViewAdapter(data);
//        adapter2 = new HomeAdapter(models);
////        rv.setAdapter(adapter);
//        tvMore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"hah",Toast.LENGTH_SHORT).show();
//                if(adapter2.getShow()){
//                    adapter2.setShow(false);
//                }else {
//                    adapter2.setShow(true);
//                }
//
//                rv.setAdapter(adapter2);
//                adapter.notifyDataSetChanged();
//            }
//        });
////        tvHead.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(getApplicationContext(),"hah",Toast.LENGTH_SHORT).show();
////            }
////        });
//
//        rv.setAdapter(adapter2);
//        adapter2.addHeaderView(getHeaderView(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(),"hah",Toast.LENGTH_SHORT).show();
//
//            }
//        }));


    }

    private View getHeaderView(View.OnClickListener listener) {
        View view = getLayoutInflater().inflate(R.layout.header_view, (ViewGroup) rv.getParent(), false);
        view.findViewById(R.id.tv).setOnClickListener(listener);

        return view;
    }




}
