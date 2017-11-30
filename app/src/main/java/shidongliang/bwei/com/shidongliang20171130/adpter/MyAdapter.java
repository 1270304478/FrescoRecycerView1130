package shidongliang.bwei.com.shidongliang20171130.adpter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import shidongliang.bwei.com.shidongliang20171130.R;
import shidongliang.bwei.com.shidongliang20171130.bean.JsonBean;

/**
 * 此类的作用：
 *
 * @author: forever
 * @date: 2017/11/30 9:05
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    List<JsonBean.DataBean> list;


    public MyAdapter(Context context, List<JsonBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String[] split = list.get(1).getImages().split("\\|");

        Uri uri= Uri.parse(split[0]);
        // GenericDraweeHierarchyBuilder builder = new GenericDraweeHierarchyBuilder();
        //  RoundingParams params = RoundingParams.asCircle();

        holder.listImage.setImageURI(uri);
        holder.listText.setText(list.get(1).getTitle());
        holder.listTime.setText(list.get(1).getCreatetime());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.list_image)
        SimpleDraweeView listImage;
        @BindView(R.id.list_text)
        TextView listText;
        @BindView(R.id.list_time)
        TextView listTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
