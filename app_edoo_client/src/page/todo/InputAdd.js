import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {Col, Container, Input, Row, Button} from "reactstrap";
// import { uuid } from 'uuidv4';
class InputAdd extends Component {
    state = {
        inputs: [{id: 1}]
    }

    render() {
        const addInput = (i) => {
            let inputs = this.state.inputs;
            // const j = require('uuidv4')
            inputs.splice(i + 1, 0, {id: 1})
            this.setState({inputs})
        }
        const removeInput=(i)=>{
            let inputs=this.state.inputs;
            inputs.splice(i,1)
            this.setState({inputs})
        }

        return (
            <div>
                <Container>
                    <Row>
                        Inputlar qatori
                    </Row>
                    {this.state.inputs.map((item, i) =>
                        <Row key={item.id}>
                            <Col md='4'>
                                <Input type="text"/>
                            </Col>
                            {this.state.inputs.length===1?
                                <Col md='1'></Col>
                                :
                                <Col md='1'>
                                    <Button color='danger'onClick={()=>removeInput(i)} >-</Button>
                                </Col>
                            }
                            <Col>
                                <Button color='success' onClick={()=>addInput(i)}>+</Button>
                            </Col>
                        </Row>)}


                </Container>

            </div>
        );
    }
}

InputAdd.propTypes = {};

export default InputAdd;
