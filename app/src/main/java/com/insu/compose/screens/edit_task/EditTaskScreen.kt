/*
Copyright 2022 Google LLC

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.insu.compose.screens.edit_task

import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.insu.compose.R.drawable as AppIcon
import com.insu.compose.R.string as AppText
import com.insu.compose.common.composable.*
import com.insu.compose.common.ext.card
import com.insu.compose.common.ext.fieldModifier
import com.insu.compose.common.ext.spacer
import com.insu.compose.common.ext.toolbarActions
import com.insu.compose.model.Priority
import com.insu.compose.model.Task
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat

@Composable
@ExperimentalMaterialApi
fun EditTaskScreen(
  popUpScreen: () -> Unit,
  taskId: String,
  modifier: Modifier = Modifier,
  viewModel: EditTaskViewModel = hiltViewModel()
) {
  val task by viewModel.task

  LaunchedEffect(Unit) { viewModel.initialize(taskId) }

  Column(
    modifier = modifier.fillMaxWidth().fillMaxHeight().verticalScroll(rememberScrollState()),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    ActionToolbar(
      title = AppText.edit_task,
      modifier = Modifier.toolbarActions(),
      endActionIcon = AppIcon.ic_check,
      endAction = { viewModel.onDoneClick(popUpScreen) }
    )

    Spacer(modifier = Modifier.spacer())

    val fieldModifier = Modifier.fieldModifier()
    BasicField(AppText.title, task.title, viewModel::onTitleChange, fieldModifier)
    BasicField(AppText.description, task.description, viewModel::onDescriptionChange, fieldModifier)
    BasicField(AppText.url, task.url, viewModel::onUrlChange, fieldModifier)

    Spacer(modifier = Modifier.spacer())
    CardEditors(task, viewModel::onDateChange, viewModel::onTimeChange)
    CardSelectors(task, viewModel::onPriorityChange, viewModel::onFlagToggle)

    Spacer(modifier = Modifier.spacer())
  }
}

@ExperimentalMaterialApi
@Composable
private fun CardEditors(
  task: Task,
  onDateChange: (Long) -> Unit,
  onTimeChange: (Int, Int) -> Unit
) {
  val activity = LocalContext.current as AppCompatActivity

  RegularCardEditor(AppText.date, AppIcon.ic_calendar, task.dueDate, Modifier.card()) {
    showDatePicker(activity, onDateChange)
  }

  RegularCardEditor(AppText.time, AppIcon.ic_clock, task.dueTime, Modifier.card()) {
    showTimePicker(activity, onTimeChange)
  }
}

@Composable
@ExperimentalMaterialApi
private fun CardSelectors(
  task: Task,
  onPriorityChange: (String) -> Unit,
  onFlagToggle: (String) -> Unit
) {
  val prioritySelection = Priority.getByName(task.priority).name
  CardSelector(AppText.priority, Priority.getOptions(), prioritySelection, Modifier.card()) {
    newValue ->
    onPriorityChange(newValue)
  }

  val flagSelection = EditFlagOption.getByCheckedState(task.flag).name
  CardSelector(AppText.flag, EditFlagOption.getOptions(), flagSelection, Modifier.card()) { newValue
    ->
    onFlagToggle(newValue)
  }
}

private fun showDatePicker(activity: AppCompatActivity?, onDateChange: (Long) -> Unit) {
  val picker = MaterialDatePicker.Builder.datePicker().build()

  activity?.let {
    picker.show(it.supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener { timeInMillis -> onDateChange(timeInMillis) }
  }
}

private fun showTimePicker(activity: AppCompatActivity?, onTimeChange: (Int, Int) -> Unit) {
  val picker = MaterialTimePicker.Builder().setTimeFormat(TimeFormat.CLOCK_24H).build()

  activity?.let {
    picker.show(it.supportFragmentManager, picker.toString())
    picker.addOnPositiveButtonClickListener { onTimeChange(picker.hour, picker.minute) }
  }
}
