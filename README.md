# Drill4J Report GitHub Action

## Project Overview

This GitHub Action is used to generate a report on the percentage of application coverage per commit in a GitHub pull
request.

## Usage

To use this action, follow these steps:

1. Obtain a GitHub API token. You can learn how to do this from the official
   GitHub [documentation](https://docs.github.com/en/authentication/keeping-your-account-and-data-secure/managing-your-personal-access-tokens).
1. Set the token as a project or organization variable. Instructions on how to do this can be found in the official
   GitHub [documentation](https://docs.github.com/en/codespaces/managing-codespaces-for-your-organization/managing-development-environment-secrets-for-your-repository-or-organization).
1. Add the following lines to your GitHub workflow after the build:

```yml 
  - uses: bodyangug/drill4j-report-github-action@v0.3
    with:
      group-id: "groupId"
      agent-id: "agentId"
      current-branch: $GITHUB_REF
      current-vcs-ref: $GITHUB_REF_NAME
      base-branch: $GITHUB_BASE_REF
      base-vcs-ref: $GITHUB_BASE_REF
      drill-address: "http://localhost:8080"
      repo-token: ${{ secrets.TOKEN }}
```

In the above code, `secrets.TOKEN` is the token you created in the previous steps.
