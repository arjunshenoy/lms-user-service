import React from 'react';
import { Admin, Resource/*, ListGuesser*/} from 'react-admin';
//import jsonServerProvider from 'ra-data-json-server';
//import {UserList, UserEdit, UserCreate} from "./users";
import {ProfileList, ProfileEdit, ProfileCreate} from "./users"
import dataProvider from './dataSource';
import Dashboard from './Dashboard';
import authProvider from './authProvider';

//connect the data provider to the REST endpoint
//const dataProvider = jsonServerProvider('https://jsonplaceholder.typicode.com');
//<Resource name="users" list={UserList} edit={UserEdit} create={UserCreate}/>
function App() {
   return (
       <Admin dashboard={Dashboard} authProvider={authProvider} dataProvider={dataProvider}>
			<Resource name="profiles" list={ProfileList} edit={ProfileEdit} create={ProfileCreate} pagination={false} sortable={false}/>
       </Admin>
   );
}

export default App;