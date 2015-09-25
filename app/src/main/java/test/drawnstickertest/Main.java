package test.drawnstickertest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main extends AppCompatActivity implements View.OnClickListener{
    SignaturePad iv;
    Canvas canvas;
    Paint paint;
    float downx = 0, downy = 0, upx = 0, upy = 0;
    LinearLayout mLineTab, linewidthView;
    GridView linecolorgrid;
    Button linebtn, linecolorbtn, linewidthbtn, linewidth1, linewidth2, linewidth3, linewidth4, clearbtn, erasebtn;
    boolean linebtnchk = false, linecolorbtnchk = false, linewidthbtnchk = false;
    LayoutInflater inflater;
    private String[] ColorIds = {"#ffffffff", "#ff000000", "#fffffff3", "#ff000005", "#fff3f4f4", "#ff00ee01", "#fffaaff7", "#ff0aa008", "#ffffffff", "#ff000000", "#fffffff3", "#ff000005", "#fff3f4f4", "#ff00ee01", "#fffaaff7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialSet();
    }

    public void initialSet() {
        linecolorgrid = (GridView) findViewById(R.id.linecolorgrid);
        linecolorgrid.setAdapter(new ColorAdapter(this));
        linecolorgrid.setOnItemClickListener(colorOnItemClickListener);
        linewidthView = (LinearLayout) findViewById(R.id.linewidthview);
        mLineTab = (LinearLayout) findViewById(R.id.linetab);
        linebtn = (Button) findViewById(R.id.linebtn);
        linecolorbtn = (Button) findViewById(R.id.linecolorbtn);
        linewidthbtn = (Button) findViewById(R.id.linewidthbtn);
        linewidth1 = (Button) findViewById(R.id.linewidth1);
        linewidth2 = (Button) findViewById(R.id.linewidth2);
        linewidth3 = (Button) findViewById(R.id.linewidth3);
        linewidth4 = (Button) findViewById(R.id.linewidth4);
        clearbtn = (Button) findViewById(R.id.clearbtn);
        erasebtn = (Button) findViewById(R.id.erasebtn);
        linebtn.setOnClickListener(this);
        linecolorbtn.setOnClickListener(this);
        linewidthbtn.setOnClickListener(this);
        linewidth1.setOnClickListener(this);
        linewidth2.setOnClickListener(this);
        linewidth3.setOnClickListener(this);
        linewidth4.setOnClickListener(this);
        clearbtn.setOnClickListener(this);
        erasebtn.setOnClickListener(this);
        iv = (SignaturePad) findViewById(R.id.mainImage);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private GridView.OnItemClickListener colorOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.e("as", position + "");
            linecolorgrid.setVisibility(View.GONE);
            mLineTab.setVisibility(View.GONE);
            linecolorbtnchk = false;
            linebtnchk = false;
            iv.setPenColor(Color.parseColor(ColorIds[position]));
            iv.setEraseMode(false);
            if (iv.mMinWidth > 7f) setPenWidth(3f);
        }
    };


    public class ColorAdapter extends BaseAdapter {
        private Context mContext;

        public ColorAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return ColorIds.length;
        }

        public Object getItem(int position) {
            return ColorIds[position];
        }

        public long getItemId(int position) {
            return position;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView imageView;
            if (convertView == null) {
                imageView = new ColorItem(mContext);
                imageView.setMinimumWidth(48);
                imageView.setMinimumHeight(48);
            } else {
                imageView = (ImageView) convertView;
            }
            imageView.setBackgroundColor(Color.parseColor(ColorIds[position]));
            return imageView;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linebtn: if (linebtnchk || linewidthbtnchk) {
                    mLineTab.setVisibility(View.GONE);
                    linecolorbtnchk = false;
                    linewidthbtnchk = false;
                } else mLineTab.setVisibility(View.VISIBLE);
                linecolorgrid.setVisibility(View.GONE);
                linewidthView.setVisibility(View.GONE);
                linebtnchk = !linebtnchk;
                break;
            case R.id.linecolorbtn: if (linecolorbtnchk) linecolorgrid.setVisibility(View.GONE); else linecolorgrid.setVisibility(View.VISIBLE);
                linewidthView.setVisibility(View.GONE);
                linewidthbtnchk = false;
                linecolorbtnchk = !linecolorbtnchk;
                break;
            case R.id.linewidthbtn: if (linewidthbtnchk) linewidthView.setVisibility(View.GONE); else linewidthView.setVisibility(View.VISIBLE);
                linecolorgrid.setVisibility(View.GONE);
                linecolorbtnchk = false;
                linewidthbtnchk = !linewidthbtnchk;
                break;
            case R.id.linewidth1:
                linewidthView.setVisibility(View.GONE);
                mLineTab.setVisibility(View.GONE);
                linewidthbtnchk = false;
                linebtnchk = false;
                setPenWidth(3f);
                break;
            case R.id.linewidth2:
                linewidthView.setVisibility(View.GONE);
                mLineTab.setVisibility(View.GONE);
                linewidthbtnchk = false;
                linebtnchk = false;
                setPenWidth(4f);
                break;
            case R.id.linewidth3:
                linewidthView.setVisibility(View.GONE);
                mLineTab.setVisibility(View.GONE);
                linewidthbtnchk = false;
                linebtnchk = false;
                setPenWidth(5f);
                break;
            case R.id.linewidth4:
                linewidthView.setVisibility(View.GONE);
                mLineTab.setVisibility(View.GONE);
                linewidthbtnchk = false;
                linebtnchk = false;
                setPenWidth(6f);
                break;
            case R.id.erasebtn: //iv.setPenColor(Color.argb(0, 0, 0, 0));
                iv.setEraseMode(true);
                break;
            case R.id.clearbtn: iv.clear();
                break;
        }
    }

    public void setPenWidth(float min) {
        iv.setMinWidth(min);
        iv.setMaxWidth(min + 4f);
    }
}

class ColorItem extends ImageView {
    public ColorItem(Context context) {
        super(context);
    }
    public ColorItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public ColorItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
