/**
 * 
                              ┏┓                  ┏┓
                            ┏┛┻━━━━━━━━━┻┻┓
                            ┃　　　　	 ┃ 　
                            ┃　　 ━　  	   ┃
                            ┃　┳┛　┗┳        ┃
                            ┃　　　　　 ┃
                            ┃　　　┻　    ┃
                            ┃　　　　　 ┃
                            ┗━┓　　　  ┏┛
                                 ┃　　　┃   神兽保佑　　　　　　　　
                                 ┃　　　┃   代码无BUG！
                                 ┃　　　┗━━━━━━┓
                                 ┃　　　　　    ┣┓
                                 ┃　　　　　    ┏┛
                                 ┗┓  ┓ ┏━━━┳┓     ━━┏┛
                                   ┃  ┫ ┫　   ┃ ┫ ┫
                                   ┗ ┻ ┛　    ┗ ┻ ┛
 */
 
package com.example.project;
import com.example.business.ChangeAddressDialog;
import com.example.business.ChangeAddressDialog.OnAddressCListener;
import com.example.three_package_city.CityPicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

/**
 * 主页面
 * @author Android
 *
 */
public class MainActivity extends Activity {

	public final static int MAP_REQUES_CODE_MAIN = 22; 
	//调用系统相册-选择图片
    private static final int IMAGE = 7312;
    private static final int CAMERA = 3872;
	
	private TextView mRegionalLinkage;
	private TextView mMapSelect;
	private TextView mSelectBusiness;
	private View mSelectPicture;
	private View mPicture;//回显的图片
	
	private EditText mDetailedAddress;
	private PopupWindow mCityPopupWindow;
	private PopupWindow mBusinessPopupWindow;
	private PopupWindow mPicturePopupWindow;
	private CityPicker cityPicker;
	private View mCityPopupView;
	private View mPicturePopupView;
	private View mBusinessPopupView;
	private Button mBtnCancel;
	private Button mBtnAscertain;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏  
		setContentView(R.layout.activity_main);
		
		initView();
		initData();
	}

	private void initData() {
		
		
		
		mSelectBusiness.setOnClickListener(new OnClickListener() {//点击选择行业
			
			@Override
			public void onClick(View v) {
				
				closeInputMethod();//关闭输入法
				
				ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(
						MainActivity.this);
				mChangeAddressDialog.setAddress("一级分类", "二级分类");
				mChangeAddressDialog.show();
				mChangeAddressDialog
						.setAddresskListener(new OnAddressCListener() {

							@Override
							public void onClick(String province, String city) {
//								// TODO Auto-generated method stub
//								Toast.makeText(MainActivity.this,
//										province + "-" + city,
//										Toast.LENGTH_LONG).show();
								if (!province.equals("一级分类")) {
									if (!city.equals("二级分类")) {
										mSelectBusiness.setText(province+" "+city);
									}else {
										mSelectBusiness.setText(province);
									}
								} 
							}
						});
				
//				mBusinessPopupWindow.showAtLocation(mRegionalLinkage, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
//				//设置layout在PopupWindow中显示的位置  
//				
//				 Button BtnBusinessCancel = (Button) mBusinessPopupView.findViewById(R.id.bt_popup_business_cancel);
//				 Button BtnBusinessAscertain = (Button) mBusinessPopupView.findViewById(R.id.bt_popup_business_ascertain);
//				
//				
//				 BtnBusinessCancel.setOnClickListener(new OnClickListener() {//选择取消
//					
//					@Override
//					public void onClick(View v) {
//						
//						mBusinessPopupWindow.dismiss();
//					}
//				});
//				
//				 BtnBusinessAscertain.setOnClickListener(new OnClickListener() {//选择确定
//					
//					@Override
//					public void onClick(View v) {
//						
//						
//						mBusinessPopupWindow.dismiss();
//						
//					}
//				});
				
			}

			
		});
		
		
		
		mSelectPicture.setOnClickListener(new OnClickListener() {//点击选择照片
			
			@Override
			public void onClick(View v) {
//				Toast.makeText(getApplicationContext(), "dianji", Toast.LENGTH_SHORT).show();
				mPicturePopupWindow.showAtLocation(mRegionalLinkage, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
				//设置layout在PopupWindow中显示的位置  
				TextView toShoot;
				TextView fromAlbumChoose;
				toShoot = (TextView) mPicturePopupView.findViewById(R.id.tv_to_shoot);
				fromAlbumChoose = (TextView) mPicturePopupView.findViewById(R.id.tv_from_album_choose);
				
				toShoot.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// 调用相机
						Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				    	startActivityForResult(intent,CAMERA);
				    	mPicturePopupWindow.dismiss();
					}
				});
				
				fromAlbumChoose.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						 //调用相册
				        Intent intent = new Intent(Intent.ACTION_PICK,
				                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				        startActivityForResult(intent, IMAGE);
						
						mPicturePopupWindow.dismiss();
						
					}
				});
				
//				mPicturePopupWindow.dismiss();
			}
		});
		
		
		
		mRegionalLinkage.setOnClickListener(new OnClickListener() {//点击选择省份城市区域
			
			@Override
			public void onClick(View v) {
//				mPopupWindow.showAsDropDown(tv_Regional_linkage, 20, 0);
				
				closeInputMethod();//关闭输入法
				
				mCityPopupWindow.showAtLocation(mRegionalLinkage, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
				//设置layout在PopupWindow中显示的位置  
			}
		});
		
		mBtnAscertain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mRegionalLinkage.setText(cityPicker.getCity_string());
				mCityPopupWindow.dismiss();
			}
		});
		 
		mBtnCancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					mCityPopupWindow.dismiss();
				}
			});
		
		mMapSelect.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根
//				startActivity(new Intent(MainActivity.this,MapSelectActivity.class));
				startActivityForResult(new Intent(MainActivity.this,MapSelectActivity.class), MAP_REQUES_CODE_MAIN);
			}
		});
		
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode==MAP_REQUES_CODE_MAIN) {
			if (resultCode==MapSelectActivity.MAP_REQUES_CODE_MS) {
				String stringExtra = data.getStringExtra("key");
				mMapSelect.setText("已选择");
				mDetailedAddress.setText(stringExtra);
			}
		}
		
		//获取图片路径
        if (requestCode == IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumns = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage, filePathColumns, null, null, null);
            c.moveToFirst();
            int columnIndex = c.getColumnIndex(filePathColumns[0]);
            String imagePath = c.getString(columnIndex);
            showImage(imagePath);
            c.close();
        }
        
        if (requestCode == CAMERA && resultCode == Activity.RESULT_OK && data != null) {
        	  Bundle bundle = data.getExtras();  
              Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
              Drawable drawable =new BitmapDrawable(bitmap);
              mPicture.setBackgroundDrawable(drawable);
              // 将图片显示在ImageView里 
        }
//		super.onActivityResult(requestCode, resultCode, data);
	}
	
	 //加载图片
    private void showImage(String imaePath){
        Bitmap bm = BitmapFactory.decodeFile(imaePath);
//        ((ImageView)findViewById(R.id.image)).setImageBitmap(bm);
        
        Drawable drawable =new BitmapDrawable(bm);
        
        mPicture.setBackgroundDrawable(drawable);
    }

	/**
	 * 初始化
	 */
	private void initView() {
		mRegionalLinkage = (TextView) findViewById(R.id.tv_Regional_linkage);
		mSelectBusiness = (TextView) findViewById(R.id.tv_item6_select_business);
		mMapSelect = (TextView) findViewById(R.id.tv_map_select);
		mDetailedAddress = (EditText) findViewById(R.id.et_item3_Detailed_Address);
		mSelectPicture = findViewById(R.id.view_item7_select_picture);
		mPicture = findViewById(R.id.view_item7_picture);
		mPicturePopupView = View.inflate(this, R.layout.popup_window_select_picture, null);
		mBusinessPopupView = View.inflate(this, R.layout.popup_window_select_business, null);
		mCityPopupView = View.inflate(this, R.layout.popup_window_regional_linkage, null);
		cityPicker= (CityPicker) mCityPopupView.findViewById(R.id.citypicker);
		mBtnCancel = (Button) mCityPopupView.findViewById(R.id.bt_popup_city_cancel);
		mBtnAscertain = (Button) mCityPopupView.findViewById(R.id.bt_popup_city_ascertain);
		 
		initCityPopupWindow();
		initPicturePopupWindow();
		initBusinessPopupWindow();
	}

	private void initBusinessPopupWindow() {
		mBusinessPopupWindow = new PopupWindow(mBusinessPopupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		mBusinessPopupWindow.setTouchable(true);
		mBusinessPopupWindow.setOutsideTouchable(true);
		mBusinessPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
		
	}

	private void initCityPopupWindow() {
		WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
		int height = wm.getDefaultDisplay().getHeight();//屏幕高度
		mCityPopupWindow = new PopupWindow(mCityPopupView, LayoutParams.MATCH_PARENT, 600, true);
        mCityPopupWindow.setTouchable(true);
        mCityPopupWindow.setOutsideTouchable(true);
        mCityPopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
		
	}
	
	private void initPicturePopupWindow(){
		
		mPicturePopupWindow = new PopupWindow(mPicturePopupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
		mPicturePopupWindow.setTouchable(true);
		mPicturePopupWindow.setOutsideTouchable(true);
		mPicturePopupWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
	}
	
	public void closeInputMethod() {
		InputMethodManager inputMethodManager = (InputMethodManager)          //关闭输入法    
				getSystemService(Context.INPUT_METHOD_SERVICE);
		inputMethodManager.hideSoftInputFromWindow(MainActivity.this.getCurrentFocus().getWindowToken(),
		InputMethodManager.HIDE_NOT_ALWAYS);
	}
	
	public void mainreturn() {
//		finish();
		System.exit(0);
	}
}
