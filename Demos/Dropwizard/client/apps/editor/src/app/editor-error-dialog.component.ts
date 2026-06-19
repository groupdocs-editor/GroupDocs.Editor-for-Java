import { Component } from '@angular/core';
import { EditorErrorDialogService } from './editor-error-dialog.service';

@Component({
  selector: 'gd-editor-error-dialog',
  templateUrl: './editor-error-dialog.component.html',
  styleUrls: ['./editor-error-dialog.component.css']
})
export class EditorErrorDialogComponent {
  constructor(public dialog: EditorErrorDialogService) {}
}
