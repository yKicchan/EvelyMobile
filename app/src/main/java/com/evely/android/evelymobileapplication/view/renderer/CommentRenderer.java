package com.evely.android.evelymobileapplication.view.renderer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.evely.android.evelymobileapplication.R;
import com.evely.android.evelymobileapplication.model.Comment;
import com.evely.android.evelymobileapplication.module.glide.GlideApp;
import com.pedrogomez.renderers.Renderer;

/**
 * Project EvelyMobileApplication
 * Working on CommentRenderer
 * Created by Shion T. Fujie on 2017/12/06.
 */
public class CommentRenderer extends Renderer<Comment> {
    @BindView(R.id.profile_photo)
    ImageView profilePhoto;
    @BindView(R.id.who)
    TextView commenter;
    @BindView(R.id.comment_body)
    TextView commentBody;
    @BindView(R.id.commented_at)
    TextView commentedAt;
    @BindView(R.id.action_reply)
    TextView actionReply;
    @BindView(R.id.action_view_replies)
    TextView actionViewReplies;
    @BindView(R.id.icon_action_view_replies)
    View iconActionViewReplies;

    @Override
    protected void setUpView(View rootView) {
        ButterKnife.bind(this, rootView);
    }

    @Override
    protected void hookListeners(View rootView) {

    }

    @Override
    protected View inflate(LayoutInflater inflater, ViewGroup parent) {
        return inflater.inflate(R.layout.list_item_review_comment, parent, false);
    }

    @Override
    public void render() {
        final Comment comment = getContent();

        final String url = comment.getCommenter().getProfilePhotUrl();
        GlideApp.with(getContext())
                .load(url)
                .fitCenter()
                .into(profilePhoto);

        final String name = comment.getCommenter().getName();
        commenter.setText(name);

        commentBody.setText(comment.getTextContent());
    }
}
