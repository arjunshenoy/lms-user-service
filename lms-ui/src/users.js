import * as React from "react";
import { 
	List,
	Datagrid,
	TextField,
	BooleanField,
	NumberField,
	DateField,
	EditButton,
	Edit,
	Create,
	SimpleForm,
    TextInput,
	DateInput,
	BooleanInput,
	NumberInput,
} from 'react-admin';

//import { ImportButton } from "react-admin-import-csv";
//import { CreateButton, ExportButton } from "ra-ui-materialui";

/*
const ListActions = (props) => {
  const { className, basePath } = props;
  return (
    <TopToolbar className={className}>
      <CreateButton basePath={basePath} />
      <ExportButton/>
      <ImportButton {...props} basePath={basePath} />
    </TopToolbar>
  );
};*/

const ProfileFilters = [
    <TextInput source="q" label="Search" alwaysOn />,
];

export const ProfileList = props => (
    <List filters={ProfileFilters} /*actions={<ListActions />}*/ {...props}>
        <Datagrid>
			<TextField source="id" />
            <TextField source="createdBy" />
            <DateField source="createdTs" />
            <TextField source="updatedBy" />
            <DateField source="updatedTs" />
            <NumberField source="departmentId" />
            <TextField source="firstName" />
            <TextField source="middleName" />
            <TextField source="lastName" />
            <TextField source="phoneNumber" />
            <TextField source="email" />
            <TextField source="gender" />
            <DateField source="dob" />
            <DateField source="dateOfJoining" />
            <TextField source="role" />
            <TextField source="address" />
            <BooleanField source="active" />
            <BooleanField source="permanent" />
			<EditButton />
        </Datagrid>
    </List>
);

export const ProfileEdit = props => (
    <Edit {...props}>
        <SimpleForm>
			<TextInput source="id" />
            <TextInput source="createdBy" />
            <DateInput source="createdTs" />
            <TextInput source="updatedBy" />
            <DateInput source="updatedTs" />
            <NumberInput source="departmentId" />
            <TextInput source="firstName" />
            <TextInput source="middleName" />
            <TextInput source="lastName" />
            <TextInput source="phoneNumber" />
            <TextInput source="email" />
            <TextInput source="gender" />
            <DateInput source="dob" />
            <DateInput source="dateOfJoining" />
            <TextInput source="role" />
            <TextInput source="address" />
            <BooleanInput source="active" />
            <BooleanInput source="permanent" />
        </SimpleForm>
    </Edit>
);

export const ProfileCreate = props => (
    <Create {...props}>
        <SimpleForm>
			<TextInput source="id" />
            <TextInput source="createdBy" />
            <DateInput source="createdTs" />
            <TextInput source="updatedBy" />
            <DateInput source="updatedTs" />
            <NumberInput source="departmentId" />
            <TextInput source="firstName" />
            <TextInput source="middleName" />
            <TextInput source="lastName" />
            <TextInput source="phoneNumber" />
            <TextInput source="email" />
            <TextInput source="gender" />
            <DateInput source="dob" />
            <DateInput source="dateOfJoining" />
            <TextInput source="role" />
            <TextInput source="address" />
            <BooleanInput source="active" />
            <BooleanInput source="permanent" />
        </SimpleForm>
    </Create>
);
