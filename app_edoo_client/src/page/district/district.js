import React, {Component} from 'react';
import PropTypes from 'prop-types';
import axios from "axios";
import {Button, Container, Input, Label, Modal, ModalBody, ModalHeader, Row, Table} from "reactstrap";
import {toast} from "react-toastify";

class District extends Component {

    state = {
        regions: [],
        districts: [],
        showModal: false,
        currentDistrict: '',
        deleteShowModal: false,
        regionId: ''
    }

    componentDidMount() {
        this.getDistrict()
        axios.get("http://localhost/api/region").then(res => {
            this.setState({regions: res.data._embedded.regions})
        })
    }

    getDistrict = () => {
        axios.get("http://localhost/api/district").then(res => {
            console.log(res)
            // this.setState({districts: res.data._embedded.regions})
        })
    }

    render() {
        const openModal = (district) => {
            this.setState({showModal: !this.state.showModal, currentDistrict: district})
        }
        const deleteModal = (district) => {
            this.setState({deleteShowModal: !this.state.deleteShowModal, currentDistrict: district})
        }

        const getNameUz = (e) => {
            this.setState({nameUz: e.target.value})
        }
        const getNameEn = (e) => {
            this.setState({nameEn: e.target.value})
        }
        const getNameRu = (e) => {
            this.setState({nameRu: e.target.value})
        }
        const getRegionId = (e) => {
            this.setState({regionId: e.target.value})
        }
        const saveRegion = () => {
            let nameUz = this.state.nameUz
            let nameRu = this.state.nameRu
            let nameEn = this.state.nameEn
            let regionId = this.state.regionId
            let obj = {nameUz, nameRu, nameEn, regionId}
            let currentDistrict = this.state.currentDistrict
            axios.post(("http://localhost/api/district" + obj))

            //     axios({
            //         url: "http://localhost/api/district" + (currentDistrict ? "/" + currentDistrict.id : ''),
            //         method: (currentDistrict) ? "PUT" : "POST",
            //         data: obj
            //     }).then(res => {
            //         if (res) {
            //             toast.success(currentDistrict ? "Edit region" : "add region")
            //             this.getDistrict()
            //             this.setState({nameUz: "", nameRu: "", nameEn: "", showModal: false, currentDistrict: ""})
            //         }
            //     })
        }

        return (
            <div>
                <Container>
                    <Button color="dark" onClick={() => openModal('')}>AddDistrict</Button>
                    <Row>
                        <Table>
                            <thead>
                            <tr>
                                <th>Tr</th>
                                <th>NameUz</th>
                                <th>NameRu</th>
                                <th>NameEn</th>
                                <th>Region name</th>
                                <th colSpan="2">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.districts.map((item, i) =>
                                <tr key={item.id}>
                                    <td>{i + 1}</td>
                                    <td>{item.nameUz}</td>
                                    <td>{item.nameRu}</td>
                                    <td>{item.nameEn}</td>
                                    <td>{item.region.nameUz}</td>
                                    <td><Button color="warning" onClick={() => openModal(item)}>Edit</Button></td>
                                    <td><Button color="danger" onClick={() => deleteModal(item)}>Delete</Button></td>
                                </tr>
                            )}
                            </tbody>
                        </Table>
                    </Row>
                </Container>

                <Modal isOpen={this.state.showModal}>
                    <ModalHeader>{this.state.currentDistrict ? "EditDistrict" : "AddDistrict"}</ModalHeader>
                    <ModalBody>
                        <Label>NameUz</Label>
                        <Input type="text" name="nameUz" id="nameUz" defaultValue={this.state.currentDistrict.nameUz}
                               onChange={getNameUz} placeholder="nameUz"/>
                        <Label>NameRu</Label>
                        <Input type="text" name=" nameRu" id=" nameRu" defaultValue={this.state.currentDistrict.nameRu}
                               onChange={getNameRu} placeholder=" nameRu"/>
                        <Label>NameEn</Label>
                        <Input type="text" name="nameEn" id="nameEn" defaultValue={this.state.currentDistrict.nameEn}
                               onChange={getNameEn} placeholder="nameEn"/>
                        <Input type="select" onChange={getRegionId}>
                            <option value="0" selected={true} disabled={true}>Region tanlang</option>
                            {this.state.regions.map((item, i) =>
                                <option value={item.id}>{item.nameUz}</option>
                            )}
                        </Input>
                        <Button color="primary" onClick={saveRegion}>Save</Button>{' '}
                        <Button onClick={openModal}>Cancel</Button>
                    </ModalBody>
                </Modal>
            </div>
        );
    }
}

District.propTypes = {};

export default District;
