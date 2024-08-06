import { Directive, ElementRef, Output, EventEmitter, HostListener } from '@angular/core';

@Directive({
  selector: '[appClickOutside]'
})
export class ClickOutsideDirective {

  @Output() clickOutside = new EventEmitter<void>();

  constructor(private elementRef: ElementRef) {}

  @HostListener('document:click', ['$event.target'])
  onClick(targetElement: EventTarget): void {
    const clickedInside = this.elementRef.nativeElement.contains(targetElement as Node);
    if (!clickedInside) {
      this.clickOutside.emit();
    }
  }

}
