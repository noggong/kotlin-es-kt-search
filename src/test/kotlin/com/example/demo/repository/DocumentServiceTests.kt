//package com.example.demo.repository
//
//import com.example.demo.document.Document
//import com.example.demo.repository.es.DocumentRepository
//import com.example.demo.service.DocumentService
//import org.junit.jupiter.api.Assertions
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.assertDoesNotThrow
//import org.mockito.InjectMocks
//import org.mockito.Mock
//import org.mockito.ArgumentMatchers.any
//import org.mockito.BDDMockito.given
//import org.springframework.boot.test.context.SpringBootTest
//import java.util.*
//
//@SpringBootTest
//class DocumentServiceTests {
//
//    @Mock
//    private lateinit var documentRepository: DocumentRepository
//    @InjectMocks
//    private lateinit var documentService: DocumentService
//
//    @BeforeEach
//    fun setUp() {
//        documentService = DocumentService(documentRepository)
//    }
//
//    @Test
//    fun save() {
//        val document = Document(1, "텍스트1")
//        given(documentRepository.save(any())).willReturn(document);
//
//        val savedDocument = documentService.save(document)
//
//        Assertions.assertAll(
//            { Assertions.assertNotNull(savedDocument)},
//            { Assertions.assertEquals(savedDocument.id, document.id)},
//            { Assertions.assertEquals(savedDocument.text, document.text)}
//        )
//    }
//
//    @Test
//    fun findById() {
//        val document = Document(1, "텍스트1")
//
//        given(documentRepository.findById(any())).willReturn(Optional.of(document))
//
//        val searchDocument = documentService.findById(1)
//
//        Assertions.assertAll(
//            { Assertions.assertNotNull(searchDocument)},
//            { Assertions.assertEquals(searchDocument.id, document.id)},
//            { Assertions.assertEquals(searchDocument.text, document.text)}
//        )
//    }
//
//    @Test
//    fun delete() {
//        val document = Document(1, "텍스트1")
//
//        given(documentRepository.findById(any())).willReturn(Optional.of(document))
//
//        assertDoesNotThrow { documentService.delete(1) }
//
//    }
//}
