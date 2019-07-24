package com.example.retrofitapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.retrofitapi.constants.Constants;
import com.example.retrofitapi.models.Item;
import com.example.retrofitapi.models.Result;
import com.example.retrofitapi.services.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddAndUpdateActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edName, edPrice, edBrand;
    Button btnSubmit;
    private Item item;

    private boolean isEdit = false;
    private int position;


    private String tempName = "",
            tempBrand = "",
            tempPrice = "";


    private final int ALERT_DIALOG_CLOSE = 10;
    private final int ALERT_DIALOG_DELETE = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_and_update);

        edName = findViewById(R.id.edName);
        edPrice = findViewById(R.id.edPrice);
        edBrand = findViewById(R.id.edBrand);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(this);

        item = getIntent().getParcelableExtra("item");
        if (item != null) {
            position = getIntent().getIntExtra("position", 0);
            isEdit = true;
        } else {
            item = new Item();
        }

        String actionBarTitle;
        String btnTitle;

        if (isEdit) {
            actionBarTitle = "Ubah";
            btnTitle = "Update";
            if (item != null) {
                edName.setText(item.getNama());
                edBrand.setText(item.getBrand());
                edPrice.setText("" + item.getPrice());

                tempPrice = "" + item.getPrice();
                tempName = item.getNama();
                tempBrand = item.getBrand();
            }
        } else {
            actionBarTitle = "Tambah";
            btnTitle = "Simpan";
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(actionBarTitle);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        btnSubmit.setText(btnTitle);

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onClick(View view) {
        if (isEdit) {
            editData();
        } else {
            addNewData();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (isEdit) {
            getMenuInflater().inflate(R.menu.menu_form, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showAlertDialog(ALERT_DIALOG_DELETE);
                break;
            case android.R.id.home:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlertDialog(int type) {
        String dialogTitle, dialogMsg;

        boolean isDialogClose = false;
        if (type == ALERT_DIALOG_CLOSE) {
            dialogTitle = "Cancel";
            dialogMsg = "Do you to cancel?";
            isDialogClose = true;
        } else {
            dialogTitle = "Delete";
            dialogMsg = "Do you to delete data?";
        }

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        final boolean finalIsDialogClose = isDialogClose;
        alertDialog.setTitle(dialogTitle)
                .setMessage(dialogMsg)
                .setPositiveButton("yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (finalIsDialogClose) {
                                    finish();
                                } else {
                                    deleteData();
                                }
                            }
                        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alertDialog1 = alertDialog.create();
        alertDialog1.show();
    }

    private void deleteData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ...");
        progressDialog.show();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        APIService apiService = retrofit.create(APIService.class);


        final Call<Result> result = apiService.delete(Constants.TOKEN, item.getId());


        result.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                Result jsonResult = response.body();

                Log.d("delete", jsonResult.toString());
                Toast.makeText(AddAndUpdateActivity.this, jsonResult.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("error", t.getMessage().toString());
                progressDialog.dismiss();
                Toast.makeText(AddAndUpdateActivity.this, "Failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    private void editData() {
        if (!checkItemData()) {
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ...");
        progressDialog.show();


        String name = edName.getText().toString().trim();
        String brand = edBrand.getText().toString().trim();
        Integer price = Integer.parseInt(edPrice.getText().toString().trim());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        APIService apiService = retrofit.create(APIService.class);

        final Call<Result> result = apiService.update(Constants.TOKEN, item.getId(), name, brand, price);

        result.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                Result jsonResult = response.body();
                Log.d("MainActivity", jsonResult.toString());

                Toast.makeText(AddAndUpdateActivity.this, jsonResult.getMessage(), Toast.LENGTH_LONG).show();

                finish();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("error", t.getMessage().toString());
                progressDialog.dismiss();

            }
        });
    }

    private void addNewData() {
        if (!checkItemData()) {
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait ...");
        progressDialog.show();

        String name = edName.getText().toString().trim();
        String brand = edBrand.getText().toString().trim();
        Integer price = Integer.parseInt(edPrice.getText().toString().trim());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL_API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        APIService apiService = retrofit.create(APIService.class);


        final Call<Result> result = apiService.create(Constants.TOKEN, name, brand, price);


        result.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                progressDialog.dismiss();

                Result jsonResult = response.body();

                Log.d("AddAndUpdateActivity", jsonResult.toString());
                Toast.makeText(AddAndUpdateActivity.this, jsonResult.getMessage(), Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                Log.d("error", t.getMessage().toString());
                progressDialog.dismiss();
                Toast.makeText(AddAndUpdateActivity.this, "Failed", Toast.LENGTH_LONG).show();

            }
        });
    }

    private boolean checkItemData() {
        boolean isOK = true;

        if (TextUtils.isEmpty(edName.getText().toString().trim())) {
            isOK = false;
            edName.setText("Please input item name");
        } else if (TextUtils.isEmpty(edBrand.getText().toString().trim())) {
            isOK = false;
            edBrand.setText("Please input item brand");

        } else if (TextUtils.isEmpty(edPrice.getText().toString().trim())) {
            isOK = false;
            edPrice.setText("Please input item price");
        }

        return isOK;
    }
}
