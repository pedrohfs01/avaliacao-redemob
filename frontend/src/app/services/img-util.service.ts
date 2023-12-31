import { Injectable } from "@angular/core";
@Injectable()
export class ImageUtilService {
    dataUriToBlob(dataURI) {
        const newUri = new String(dataURI);
        var byteString = atob(newUri.split(',')[1]);
        var mimeString = newUri.split(',')[0].split(':')[1].split(';')[0]
        var ab = new ArrayBuffer(byteString.length);
        var ia = new Uint8Array(ab);
        for (var i = 0; i < byteString.length; i++) {
            ia[i] = byteString.charCodeAt(i);
        }
        return new Blob([ab], { type: mimeString });
    }
}
