package com.orgzly.android.git;

import android.net.Uri;
import android.util.Log;

import com.orgzly.R;
import com.orgzly.android.data.DataRepository;
import com.orgzly.android.prefs.AppPreferences;
import com.orgzly.android.prefs.RepoPreferences;

public class GitPreferencesFromRepoPrefs implements GitPreferences {
    private RepoPreferences repoPreferences;
    private DataRepository repo;

    public GitPreferencesFromRepoPrefs(RepoPreferences prefs, DataRepository repoIn) {
        repoPreferences = prefs;
        repo = repoIn;
    }

    @Override
    public String sshKeyPathString() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_ssh_key_path, "orgzly");
    }

    @Override
    public String getAuthor() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_author, "orgzly");
    }

    @Override
    public String getEmail() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_email, "");
    }

    @Override
    public String repositoryFilepath() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_repository_filepath,
                AppPreferences.repositoryStoragePathForUri(
                        repoPreferences.getContext(), remoteUri()));
    }

    @Override
    public String remoteName() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_remote_name, "origin");
    }

    @Override
    public String branchName() {
        return repoPreferences.getStringValueWithGlobalDefault(
                R.string.pref_key_git_branch_name, "master");
    }

    @Override
    public Uri remoteUri() {
        String uriString = repo.getRepo(repoPreferences.getRepoId()).getUrl();
        return Uri.parse(uriString);

    }
}
