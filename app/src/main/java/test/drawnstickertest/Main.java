package test.drawnstickertest;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by Schrws on 2015-07-23.
 */
public class Main extends AppCompatActivity implements View.OnClickListener{
    DrawPad eiv;
    LinearLayout mLineTab, linewidthView, linebtnbackground, erasebtnbackground;
    GridView linecolorgrid;
    Button linebtn, linecolorbtn, linewidthbtn, linewidth1, linewidth2, linewidth3, linewidth4, clearbtn, erasebtn;
    boolean linebtnchk = false, linecolorbtnchk = false, linewidthbtnchk = false;
    LayoutInflater inflater;
    private String[] ColorIds = {"#ffff0000", "#ffff5e00", "#ffffbb00", "#ff1ddb16", "#ff0100ff", "#ff000000", "#fff15f5f", "#fff29661", "#fff2cb61", "#ff86e57f", "#ff6b66ff", "#ffffffff",
            "#ffcc3d3d", "#ffcc723d", "#ffcca63d", "#ff47c83e", "#ff4641d9", "#ff8c8c8c", "#ff980000", "#ff993800", "#ff997000", "#ff2f9d27", "#ff050099", "#ff5d5d5d",
            "#ff670000", "#ff662500", "#ff664b00", "#ff22741c", "#ff030066", "#ff353535"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initialSet();
    }

    private GridView.OnItemClickListener colorOnItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            linecolorgrid.setVisibility(View.GONE);
            mLineTab.setVisibility(View.GONE);
            linecolorbtnchk = false;
            linebtnchk = false;
            eiv.setPenColor(Color.parseColor(ColorIds[position]));
            eiv.setEraseMode(false);
            if (eiv.mMinWidth > 7f) {
                eiv.setMinWidth(3f);
                eiv.setMaxWidth(7f);
            }
            linebtn.setBackgroundResource(R.drawable.penselected);
            linebtnbackground.setBackgroundColor(Color.parseColor(ColorIds[position]));
            erasebtnbackground.setBackgroundColor(Color.parseColor("#ffffffff"));
        }
    };

    public void modifyPenWidth(float min) {
        linewidthView.setVisibility(View.GONE);
        mLineTab.setVisibility(View.GONE);
        linewidthbtnchk = false;
        linebtnchk = false;
        eiv.setMinWidth(min);
        eiv.setMaxWidth(min + 4f);
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
                modifyPenWidth(3f);
                break;
            case R.id.linewidth2:
                modifyPenWidth(4f);
                break;
            case R.id.linewidth3:
                modifyPenWidth(5f);
                break;
            case R.id.linewidth4:
                modifyPenWidth(6f);
                break;
            case R.id.erasebtn:
                linebtn.setBackgroundResource(R.drawable.pen);
                erasebtnbackground.setBackgroundColor(Color.parseColor("#ff865239"));
                eiv.setEraseMode(true);
                break;
            case R.id.clearbtn: eiv.clear();
                break;
        }
    }

    public void initialSet() {
        linebtnbackground = (LinearLayout) findViewById(R.id.linebtnbackground);
        erasebtnbackground = (LinearLayout) findViewById(R.id.erasebtnbackground);
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
        eiv = (DrawPad) findViewById(R.id.mainImage);
        inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

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
            } else imageView = (ImageView) convertView;

            imageView.setBackgroundColor(Color.parseColor(ColorIds[position]));
            return imageView;
        }
    }

    public class ColorItem extends ImageView {
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
}
