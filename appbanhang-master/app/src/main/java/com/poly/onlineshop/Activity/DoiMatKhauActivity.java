package com.poly.onlineshop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.poly.onlineshop.R;
import com.poly.onlineshop.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class DoiMatKhauActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText ed_pass_cu, ed_pass_moi;
    Button btndoimatkhau;
    User user1;
    ProgressDialog progressDialog;
    String matkhaucu;
    List<User> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);
        toolbar = findViewById(R.id.toolbar_doipass);
//        ed_pass_cu = findViewById(R.id.ed_pass_cu);
        ed_pass_moi = findViewById(R.id.ed_pass_moi);
        btndoimatkhau = findViewById(R.id.btn_doi_mat_khau);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        progressDialog = new ProgressDialog(this);
        userList = new ArrayList<>();
        //get pass cu
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
//        String idUser;
//        idUser = user.getUid();
//        reference.child(idUser).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                user1 = snapshot.getValue(User.class);
//                matkhaucu = user1.getPass();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
        btndoimatkhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String passcu = ed_pass_cu.getText().toString().trim();
                String passmoi = ed_pass_moi.getText().toString().trim();
//                if (passcu.equals("")) {
//                    Toast.makeText(DoiMatKhauActivity.this, "Vui l??ng nh???p m???t kh???u c??", Toast.LENGTH_SHORT).show();
//                    return;
//                } else
                if (passmoi.equals("")) {
                    Toast.makeText(DoiMatKhauActivity.this, "Vui l??ng nh???p m???t kh???u m???i", Toast.LENGTH_SHORT).show();
                    return;
                }
//                    else if (matkhaucu != passcu) {
//                    Toast.makeText(DoiMatKhauActivity.this, "M???t kh???u c?? kh??ng ????ng", Toast.LENGTH_SHORT).show();
//                    return;
//                }
                else if (passmoi.length() < 6) {
                    Toast.makeText(DoiMatKhauActivity.this, "M???t kh???u ph???i tr??n 6 s???", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    updatePass();
                }
            }
        });
    }

    private void updatePass() {
        progressDialog.show();
        String passmoi = ed_pass_moi.getText().toString().trim();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.updatePassword(passmoi)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DoiMatKhauActivity.this, "?????i m???t kh???u th??nh c??ng", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(DoiMatKhauActivity.this, "?????i m???t kh???u kh??ng th??nh c??ng, vui l??ng ????ng nh???p l???i", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}