package com.f0hzz52.retrofit2example;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public Context context;
    private List<User> userList;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    public RecyclerViewAdapter(Context context, List<User> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvName.setText(userList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    public void loadListItem() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<User>> call = service.getUser("dumposk129");
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Response<List<User>> response) {
                if (response.isSuccess() && response.body() != null) {

                    userList = response.body();

                    setDataAdapter();
                } else if (response.isSuccess() && response.body().isEmpty()) {
                    Log.d("Response Body isNull", response.message());
                } else {
                    // 404 Not Found
                    Log.d("Response Error Body", response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {
                // DO NETWORK ERROR HANDLER
                Log.d("Error", t.getMessage());
            }
        });

    }

    private void setDataAdapter() {
        recyclerViewAdapter = new RecyclerViewAdapter(context, userList);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    public void sendRecyclerView(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tvName)
        TextView tvName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, tvName.getText(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
