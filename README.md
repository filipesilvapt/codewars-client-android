# Codewars Client (Android)
An Android client for the codewars public API.

## 7 Days Challenge Requirements
This project was created as a challenge to fulfill as many requirements as possible within a 7 day period.
The requirements are as follow:

##### Utilise the Codewars API: https://dev.codewars.com/#rest-api

##### Application overview:
1. Show view to find a specific member and to list the found members
2. Show view listing the completed and authored challenges
3. Show details view of a completed or authored challenge

##### Functional requirements:

1. Members search
The application should have a screen that allows the user to search for a member by name.
The same view should also show a list with the last 5 members that have been searched for and found.
The list with members should show basic information such as name, rank and best language with correponding points for it.
By default, the members should be displayed in order of time of search, but there should be an option to re-order them by rank.
Here is a link to the current leaderboard where the members search can be experienced: https://www.codewars.com/users/leaderboard

2. Members challenges (completed/authored)
On member selection you should display a view with bottom navigation and a list of challenges.
The bottom navigation menu options should have the completed challenges and authored challenges, each of them presenting the user with a corresponding list of items.
The list should be populated with more results when the end of the page is reached, unless there are no more results to be fetched, in which case the user should be notified of this.

3. Challenge details
When a challenge is selected (completed or authored), a new screen should be displayed showing its details.

##### Technical acceptance criteria:
1. Solid principles in practice with clean code, test coverage, error handling
2. MVP / MVVM design pattern
3. Android architecture components
4. Reactive approach
5. Repository pattern with cache policy implemented
6. Dagger 2

##### Bonus aspects:
1. Espresso coverage
2. Code written in Kotlin
3. Offline functionality

## License
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
