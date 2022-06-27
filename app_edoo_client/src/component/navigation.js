import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {
    Collapse, DropdownItem, DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand, NavbarText,
    NavbarToggler,
    NavItem,
    NavLink,
    UncontrolledDropdown
} from "reactstrap";

class Navigation extends Component {
    render() {
        return (
            <div>
                <Navbar
                    color="warning"
                    expand="md"
                    light
                >
                    <NavbarBrand href="/">
                        reactstrap
                    </NavbarBrand>
                    <NavbarToggler onClick={function noRefCheck(){}} />
                    <Collapse navbar>
                        <Nav
                            className="me-auto"
                            navbar
                        >
                            <NavItem>
                                <NavLink href="/country">Country</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink href="/region">Region</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink href="/district">District</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink href="/todo">Todo</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink href="/inputAdd">Input</NavLink>
                            </NavItem>
                            <NavItem>
                                <NavLink>
                                    GitHub
                                </NavLink>
                            </NavItem>
                            <UncontrolledDropdown
                                inNavbar
                                nav
                            >
                                <DropdownToggle
                                    caret
                                    nav
                                >
                                    Options
                                </DropdownToggle>
                                <DropdownMenu right>
                                    <DropdownItem>
                                        Option 1
                                    </DropdownItem>
                                    <DropdownItem>
                                        Option 2
                                    </DropdownItem>
                                    <DropdownItem divider />
                                    <DropdownItem>
                                        Reset
                                    </DropdownItem>
                                </DropdownMenu>
                            </UncontrolledDropdown>
                        </Nav>
                        <NavbarText>
                            BackEnd_01
                        </NavbarText>
                    </Collapse>
                </Navbar>
            </div>
        );
    }
}

Navigation.propTypes = {};

export default Navigation;
