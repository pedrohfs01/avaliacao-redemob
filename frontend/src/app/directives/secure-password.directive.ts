import { Directive, Input } from '@angular/core';
import { NG_VALIDATORS, AbstractControl, ValidationErrors, Validator } from '@angular/forms';

@Directive({
    selector: '[appSecurePassword]',
    providers: [
        { provide: NG_VALIDATORS, useExisting: SecurePasswordDirective, multi: true }
    ]
})
export class SecurePasswordDirective implements Validator {
    @Input('appSecurePassword') appSecurePassword: boolean;

    validate(control: AbstractControl): ValidationErrors | null {
        if (!this.appSecurePassword) {
            return null;
        }

        const password = control.value;
        const hasUppercase = /[A-Z]/.test(password);
        const hasLowercase = /[a-z]/.test(password);
        const hasNumber = /\d/.test(password);
        const hasSpecialCharacter = /[!@#$%^&*()_+[\]{};':"\\|,.<>/?-]/.test(password);

        if (!hasUppercase || !hasLowercase || !hasNumber || !hasSpecialCharacter || password.length < 8 || password.lenght > 20) {
            return { insecurePassword: true };
        }

        return null;
    }
}