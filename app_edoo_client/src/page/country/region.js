import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Button, Container, Input, Label, Modal, ModalBody, ModalFooter, ModalHeader, Row, Table} from "reactstrap";
import axios from "axios";
import {toast} from "react-toastify";

class Region extends Component {
    state = {
        regions: [],
        showModal: false,
        deleteShowModal: false,
        currentRegion: '',
        nameUz: '',
        nameRu: '',
        nameEn: '',
    }

    componentDidMount() {
        this.getRegions()
    }
    getRegions = () => {
        axios.get("http://localhost/api/region").then(res => {
            this.setState({regions: res.data._embedded.regions})
        })
    }
    render() {
        const openModal = (region) => {
            this.setState({showModal: !this.state.showModal,currentRegion: region})
        }
        const deleteModal = (region) => {
            this.setState({deleteShowModal: !this.state.deleteShowModal, currentRegion: region})
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

        const saveRegion = () => {
            let nameUz = this.state.nameUz
            let nameRu = this.state.nameRu
            let nameEn = this.state.nameEn
            let obj = {nameUz, nameRu, nameEn}
            let currentRegion=this.state.currentRegion
            axios({
                url: "http://localhost/api/region"+(currentRegion?"/"+currentRegion.id:''),
                method: (currentRegion)?"PUT":"POST",
                data: obj
            }).then(res=>{
                if (res){
                    toast.success(currentRegion?"Edit region":"add region")
                    this.getRegions()
                    this.setState({nameUz: "", nameRu: "", nameEn: "", showModal: false, currentRegion: ""})
                }
            })
        }

        const deleteRegion = () => {
            let region=this.state.currentRegion
            axios.delete("http://localhost/api/region/"+region.id).then(res=>{
                if (res.status===204){
                    this.getRegions()
                    toast.success("Region uchirildi")
                    this.setState({deleteShowModal:false, currentRegion:''})
                }
            })
        }
        return (
            <div>
                <Container>
                    <Button color="dark" onClick={()=>openModal('')}>AddRegion</Button>
                    <Row>
                        <Table>
                            <thead>
                            <tr>
                                <th>Tr</th>
                                <th>NameUz</th>
                                <th>NameRu</th>
                                <th>NameEn</th>
                                <th colSpan="2">Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            {this.state.regions.map((item, i) =>
                                <tr key={item.id}>
                                    <td>{i + 1}</td>
                                    <td>{item.nameUz}</td>
                                    <td>{item.nameRu}</td>
                                    <td>{item.nameEn}</td>
                                    <td><Button color="warning" onClick={()=>openModal(item)}>Edit</Button></td>
                                    <td><Button color="danger" onClick={() => deleteModal(item)}>Delete</Button></td>
                                </tr>
                            )}
                            </tbody>
                        </Table>
                    </Row>
                </Container>
                <Modal isOpen={this.state.showModal}>
                    <ModalHeader>{this.state.currentRegion ? "EditRegion" : "AddRegion"}</ModalHeader>
                    <ModalBody>
                        <Label>NameUz</Label>
                        <Input type="text" name="nameUz" id="nameUz" defaultValue={this.state.currentRegion.nameUz} onChange={getNameUz} placeholder="nameUz"/>
                        <Label>NameRu</Label>
                        <Input type="text" name=" nameRu" id=" nameRu" defaultValue={this.state.currentRegion.nameRu} onChange={getNameRu} placeholder=" nameRu"/>
                        <Label>NameEn</Label>
                        <Input type="text" name="nameEn" id="nameEn"  defaultValue={this.state.currentRegion.nameEn} onChange={getNameEn} placeholder="nameEn"/>
                        <Button color="primary" onClick={saveRegion}>Save</Button>{' '}
                        <Button onClick={openModal}>Cancel</Button>
                    </ModalBody>
                </Modal>

                <Modal isOpen={this.state.deleteShowModal}>
                    <ModalHeader>Are you sure delete {this.state.currentRegion.nameUz} </ModalHeader>
                    <ModalFooter>
                        <Button color="success" onClick={deleteRegion}>Yes</Button>
                        <Button color="danger" onClick={deleteModal}>No</Button>
                    </ModalFooter>
                </Modal>
            </div>
        );
    }
}

export default Region;