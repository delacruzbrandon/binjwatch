//package com.brhdc.binjwatch.presentation.screens.details
//
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.res.stringResource
//import com.brhdc.binjwatch.R
//import com.brhdc.binjwatch.util.Action
////
////@Composable
////fun MainAppBar(
////    selectedMovie: Movie?,
////    destination: (Action) -> Unit
////) {
////    if (selectedMovie == null) {
////        NewTaskAppBar(navigateTo = destination)
////    } else {
////        ExistingTaskAppBar(
////            selectedToDoTask = selectedToDoTask,
////            navigateToListScreen = navigateToListScreen
////        )
////    }
////}
//
//@Composable
//fun MainAppBar(
//    navigateTo: (Action) -> Unit
//) {
//    ExistingTaskAppBar { }(
//        navigationIcon = {
//            CloseAction(onBackClicked = navigateToListScreen)
//        },
//        title = {
//            Text(
//                text = stringResource(id = R.string.add_task),
//                color = MaterialTheme.colors.topAppBarContentColor
//            )
//        },
//        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor,
//        actions = {
//            AddAction(onAddClicked = navigateToListScreen)
//        }
//    )
//}
//
//@Composable
//fun BackAction(
//    onBackClicked: (Action) -> Unit
//) {
//    IconButton(onClick = { onBackClicked(Action.NO_ACTION) }) {
//        Icon(
//            imageVector = Icons.Filled.ArrowBack,
//            contentDescription = stringResource(id = R.string.back_arrow),
//            tint = MaterialTheme.colors.topAppBarContentColor
//        )
//    }
//}
//
//@Composable
//fun AddAction(
//    onAddClicked: (Action) -> Unit
//) {
//    IconButton(onClick = { onAddClicked(Action.ADD) }) {
//        Icon(
//            imageVector = Icons.Filled.Check,
//            contentDescription = stringResource(id = R.string.add_task),
//            tint = MaterialTheme.colors.topAppBarContentColor
//        )
//    }
//}
//
//@Composable
//fun DeleteAction(
//    onDeleteClicked: (Action) -> Unit
//) {
//    IconButton(onClick = { onDeleteClicked(Action.REMOVE) }) {
//        Icon(
//            imageVector = Icons.Filled.Delete,
//            contentDescription = stringResource(id = R.string.button_delete),
//            tint = MaterialTheme.colors.topAppBarContentColor
//        )
//    }
//}
//
//@Composable
//@Preview
//fun NewTaskBarPreview() {
//    NewTaskAppBar({})
//}
//
//@Composable
//@Preview
//fun ExistingTaskBarPreview() {
//    ExistingTaskAppBar(
//        TaskModel(0, "Groceries", "", Priority.MAJOR),
//        {}
//    )
//}