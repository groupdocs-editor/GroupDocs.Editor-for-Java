import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class EditorErrorDialogService {
  visible = false;
  message = '';

  show(message: string): void {
    this.message = message || 'An unexpected error occurred.';
    this.visible = true;
  }

  close(): void {
    this.visible = false;
    this.message = '';
  }
}
