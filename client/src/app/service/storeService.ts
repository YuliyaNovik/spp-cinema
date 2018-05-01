
import { Injectable } from '@angular/core';

@Injectable()
export class StoreService {
    private static store: Array<any> = [];
    
    public static push(data: any) {
        this.store.push(data);
    }

    public static pop(): any {
        if (this.store.length > 0) {
            return this.store.pop();
        } else {
            return null;
        }
    }
}
