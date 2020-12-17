package space.almoder.therhombus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import space.almoder.therhombus.Shop.ShopActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void onMenuClick(View v){
        Intent intent;
        switch (v.getId()) {
            case R.id.mb1:
                intent = new Intent(this, Campaign.class);
                intent.putExtra("image", getIntent().getIntExtra("image", R.drawable.cross));
                startActivity(intent);
                break;
            case R.id.mb2:
                intent = new Intent(this, CustomGame.class);
                startActivity(intent);
                break;
            case R.id.mb3:
                startActivityForResult(new Intent(this, Settings.class),0);
                break;
            case R.id.mb4:
                intent = getIntent();
                setResult(RESULT_OK, intent);
                this.finish();
                break;
            case R.id.mb5:
                intent = new Intent(this, ShopActivity.class);
                startActivity(intent);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + v.getId());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode){
            case 0:
                finish();
                break;
            default:
                super.onActivityResult(requestCode, resultCode, data);
                break;
        }
    }
    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED, getIntent());
        super.onBackPressed();
    }
}