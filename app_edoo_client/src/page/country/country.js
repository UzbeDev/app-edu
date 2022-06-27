import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Button, Input, Label, Modal, ModalBody, ModalFooter, ModalHeader, Table} from "reactstrap";
class Country extends Component {

    state = {
        showModal: false,
        countries: [],
        countryName: '',
        currentCountry: ''
    }


    render() {
        const openModal = (davlat) => {
            this.setState({showModal: !this.state.showModal, currentCountry: davlat})
        }

        const getCountryName = (e) => {
            this.setState({countryName: e.target.value})
        }
        const saveCountry = () => {
            let name = this.state.countryName;
            let davlatlar = this.state.countries;
            let hozirgiDavlat = this.state.currentCountry;
            let newDavlat = '';
            if (name) {
                if (hozirgiDavlat) {
                    newDavlat = {id: hozirgiDavlat.id, name: name};
                    let index = davlatlar.indexOf(hozirgiDavlat);
                    davlatlar.splice(index, 1, newDavlat);
                } else {
                    let maxId = davlatlar.length === 0 ? 1 : davlatlar[davlatlar.length - 1].id + 1;
                    newDavlat = {id: maxId, name: name}
                    davlatlar.push(newDavlat)
                }
            }
            this.setState({countries: davlatlar, showModal: false, countryName: ''})
        }

        return (
            <div className="container">
                <div className="row">
                    <div>
                        <Button color="primary" onClick={()=>openModal("")}>AddCountry</Button>
                        <Table>
                            <thead>
                            <tr>
                                <th>TR</th>
                                <th>Name</th>
                                <th colSpan="2">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.countries.map((item, i) =>
                                <tr>
                                    <td>{i + 1}</td>
                                    <td>{item.name}</td>
                                    <td><Button color="warning" onClick={() => openModal(item)}>Edit</Button></td>
                                    <td><Button color="success">Delete</Button></td>
                                </tr>
                            )}
                            </tbody>
                        </Table>
                    </div>
                </div>

                <Modal isOpen={this.state.showModal}>
                    <ModalHeader>{this.state.currentCountry ? "EditCountry" : "AddCountry"}</ModalHeader>
                    <ModalBody>
                        <Label id='name'>Country name</Label>
                        <Input name="name" type='text' id='name' onChange={getCountryName}
                               placeholder='Please enter country name' defaultValue={this.state.currentCountry.name}/>
                    </ModalBody>
                    <ModalFooter>
                        <Button color="primary" onClick={saveCountry}>Save</Button>{' '}
                        <Button onClick={openModal}>Cancel</Button>
                    </ModalFooter>
                </Modal>
            </div>

        );
    }
}

Country.propTypes = {};

export default Country;
