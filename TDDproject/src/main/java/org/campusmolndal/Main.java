package org.campusmolndal;

/*
 * ----------------------------------------------------------------------------
 * Copyright (c) 2019-2023 Marcus Medina, Campus Mölndal
 * Licensed under the Apache License, Version 3.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 * ----------------------------------------------------------------------------
 */
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Keyreader keyreader = new Keyreader();
        String connectionString = keyreader.readConnectionString();
        MongoDBFacade mongoDBFacade = new MongoDBFacade(connectionString, "TODO");

        Todo todo1 = new Todo(1, "Buy milk", false);
        Todo todo2 = new Todo (2, "Buy bread", false);
        Todo todo3 = new Todo (3, "Buy butter", false);
        Todo todo4 = new Todo (4, "Buy cheese", false);
        Todo todo5 = new Todo (5, "Buy ham", false);
        Todo todo6 = new Todo (6, "Buy eggs", false);
        Todo todo7 = new Todo (7, "Buy bacon", false);
        Todo todo8 = new Todo (8, "Buy sausages", false);
        Todo todo9 = new Todo (9, "Buy beans", false);
        Todo todo10 = new Todo (10, "Buy tomatoes", false);



    }
}

