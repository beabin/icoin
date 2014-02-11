/*
 * Copyright (c) 2010-2012. Axon Framework
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.icoin.trading.users.query;

import com.homhon.mongo.domainsupport.modelsupport.entity.AuditAwareEntitySupport;
import com.icoin.trading.users.domain.model.user.Identifier;
import com.icoin.trading.users.domain.model.user.UserAccount;


/**
 * @author Jettro Coenradie
 */
public class UserEntry extends AuditAwareEntitySupport<UserEntry, String, Long> implements UserAccount<UserEntry> {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String realName;
    private String email;
    private Identifier identifier;

    @Override
    public String getId() {
        return username;
    }

    @Override
    public String getName() {
        return username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPrimaryKey() {
        return this.primaryKey;
    }

    @Override
    public String getUserName() {
        return this.username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Identifier getIdentifier() {
        return identifier;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setIdentifier(Identifier identifier) {
        this.identifier = identifier;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String getFullName() {
        return "" + lastName + " " + firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserEntry{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", identifier=" + identifier +
                '}';
    }

    @Override
    public boolean sameValueAs(UserEntry userEntry) {
        return super.sameIdentityAs(userEntry);
    }

    @Override
    public UserEntry copy() {
        final UserEntry userEntry = new UserEntry();

        userEntry.setPassword(password);
        userEntry.setUsername(username);
        userEntry.setPrimaryKey(primaryKey);
        userEntry.setLastName(lastName);
        userEntry.setFirstName(firstName);
        userEntry.setIdentifier(identifier);

        return userEntry;
    }
}
