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
  - uses: bodyangug/drill4j-report-github-action@v0.2
    with:
      groupId: "groupId"
      agentId: "agentId"
      currentBranch: $GITHUB_REF
      currentVcsRef: $GITHUB_REF_NAME
      baseBranch: $GITHUB_BASE_REF
      baseVcsRef: $GITHUB_BASE_REF
      drillHost: "localhost"
      drillPort: 8080
      repoToken: ${{ secrets.TOKEN }}
```

In the above code, `secrets.TOKEN` is the token you created in the previous steps.
