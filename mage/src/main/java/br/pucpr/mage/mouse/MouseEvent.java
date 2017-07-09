/*
 * Copyright 2017 luis.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.pucpr.mage.mouse;

/**
 *
 * @author luis
 */
public class MouseEvent {
    
    private final boolean altDown;
    private final boolean shiftDown;
    private final boolean ctrlDown;
    private final boolean superDown;

    MouseEvent(boolean altDown, boolean shiftDown, boolean ctrlDown, boolean superDown) {
        this.altDown = altDown;
        this.shiftDown = shiftDown;
        this.ctrlDown = ctrlDown;
        this.superDown = superDown;
    }

    
    
    public boolean isAltDown() {
        return altDown;
    }


    public boolean isShiftDown() {
        return shiftDown;
    }

    public boolean isCtrlDown() {
        return ctrlDown;
    }

    public boolean isSuperDown() {
        return superDown;
    }    
    
}
